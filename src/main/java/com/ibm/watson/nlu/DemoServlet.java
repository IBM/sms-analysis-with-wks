/* Copyright IBM Corp. 2015
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.ibm.watson.nlu;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.fluent.Response;
import org.apache.http.entity.ContentType;

import com.ibm.json.java.JSONArray;
import com.ibm.json.java.JSONObject;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.AnalysisResults;

@MultipartConfig
public class DemoServlet extends HttpServlet {

	private static Logger logger = Logger.getLogger(DemoServlet.class.getName());
	private static final long serialVersionUID = 1L;

	private String serviceName = "natural-language-understanding";

	// If running locally complete the variables below
	// with the information in VCAP_SERVICES
	private String baseURL = "<url>";
	private String username = "<username>";
	private String password = "<password>";
	private String apikey = "<apikey>";
	private String modelId = "";
	private boolean useIamApiKey = true;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.getRequestDispatcher("/index.jsp").forward(req, resp);
	}

	/**
	 * Create and POST a request to the Personality Insights service
	 *
	 * @param req
	 *            the Http Servlet request
	 * @param resp
	 *            the Http Servlet pesponse
	 * @throws ServletException
	 *             the servlet exception
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	@Override
	protected void doPost(final HttpServletRequest req, final HttpServletResponse resp)
			throws ServletException, IOException {
		logger.info("doPost");

		req.setCharacterEncoding("UTF-8");

		resp.setContentType("application/json; charset=UTF-8");

		// create the request
		String text = req.getParameter("text");
		String language = req.getParameter("language");
		String locale = req.getLocale().toString().replace("_", "-");

		try {
			SimpleNLUClient client = new SimpleNLUClient();
			if (useIamApiKey) {
				client.initIamService(apikey, baseURL);
			} else {
				client.initService(username, password, baseURL);
			}

			AnalysisResults response = client.analyze(modelId,text);
			if(response!=null)
			{
				resp.getWriter().println(response);
			}
			else
			{
				String errorResponse="There is no response from server, Please try again later";
				resp.sendError(HttpStatus.SC_BAD_GATEWAY, errorResponse );
			}
		} catch (Exception e) {
			logger.log(Level.SEVERE, "Service error: " + e.getMessage(), e);
			resp.sendError(HttpStatus.SC_BAD_GATEWAY, e.toString() );
		}
	}

	@Override
	public void init() throws ServletException {
		super.init();
		if (! getConfigParams()) {
			processVCAPServices();
			modelId = System.getenv("MODEL_ID");

			if (modelId == null) {
				// if no model ID found, set it to a value that will
				// let the user know what the issue is
				modelId = "no_model_id_found";
			}
			logger.info("modelId = " + modelId);
		}
	}
	/**
	 * If set, use user provided config params
	 */
	private boolean getConfigParams() {
		logger.info("Processing config properties");
		String configFile = "/config.properties";

		try {
			Properties props = new Properties();
			props.load(this.getClass().getResourceAsStream(configFile));
			baseURL = props.getProperty("NATURAL_LANGUAGE_UNDERSTANDING_URL");
			apikey = props.getProperty("NATURAL_LANGUAGE_UNDERSTANDING_IAM_APIKEY");
			username = props.getProperty("NATURAL_LANGUAGE_UNDERSTANDING_USERNAME");
			password = props.getProperty("NATURAL_LANGUAGE_UNDERSTANDING_PASSWORD");
			modelId = props.getProperty("WATSON_KNOWLEDGE_STUDIO_MODEL_ID");
			logger.info("baseURL = " + baseURL);
			logger.info("apikey = " + apikey);
			logger.info("username = " + username);
			logger.info("password = " + password);
			logger.info("modelId = " + modelId);
		} catch (Exception e) {
			logger.log(Level.SEVERE, "Reading config properties error: " + e.getMessage(), e);
			return false;
		}

		// make sure we have what we need
		if (modelId.equals("<add_model_id>")) {
			return false;
		}

		if (baseURL.equals("<add_nlu_url>")) {
			return false;
		}

		if ((apikey.equals("add_nlu_iam_apikey")) &&
			(username.equals("<add_nlu_username>") || password.equals("<add_nlu_password>"))) {
			return false;
		}

		if (apikey.equals("add_nlu_iam_apikey")) {
			useIamApiKey = false;
		}

		logger.info("using apikey = " + useIamApiKey);

		return true;
	}
	/**
	 * If exists, process the VCAP_SERVICES environment variable in order to get
	 * the username, password and baseURL
	 */
	private void processVCAPServices() {
		logger.info("Processing VCAP_SERVICES");
		JSONObject sysEnv = getVCAPServices();
		if (sysEnv == null)
			return;
		logger.info("Looking for: " + serviceName);

		for (Object key : sysEnv.keySet()) {
			String keyString = (String) key;
			logger.info("found key: " + key);
			if (keyString.startsWith(serviceName)) {
				JSONArray services = (JSONArray) sysEnv.get(key);
				JSONObject service = (JSONObject) services.get(0);
				JSONObject credentials = (JSONObject) service
						.get("credentials");
				baseURL = (String) credentials.get("url");
				apikey = (String) credentials.get("apikey");
				username = (String) credentials.get("username");
				password = (String) credentials.get("password");
				if (apikey == null || apikey.isEmpty()) {
					useIamApiKey = false;
				}
				logger.info("baseURL  = " + baseURL);
				logger.info("apikey = " + apikey);
				logger.info("username = " + username);
				logger.info("password = " + password);
				logger.info("using apikey = " + useIamApiKey);
			} else {
				logger.info("Doesn't match /^" + serviceName + "/");
			}
		}
	}

	/**
	 * Gets the <b>VCAP_SERVICES</b> environment variable and return it as a
	 * JSONObject.
	 *
	 * @return the VCAP_SERVICES as Json
	 */
	private JSONObject getVCAPServices() {
		String envServices = System.getenv("VCAP_SERVICES");
		if (envServices == null)
			return null;
		JSONObject sysEnv = null;
		try {
			sysEnv = JSONObject.parse(envServices);
		} catch (IOException e) {
			// Do nothing, fall through to defaults
			logger.log(Level.SEVERE,
					"Error parsing VCAP_SERVICES: " + e.getMessage(), e);
		}
		return sysEnv;
	}

}
