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

import com.ibm.json.java.JSONArray;
import com.ibm.json.java.JSONObject;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.AnalysisResults;
import org.apache.http.HttpStatus;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

@MultipartConfig
public class DemoServlet extends HttpServlet {

	private static Logger logger = Logger.getLogger(DemoServlet.class.getName());
	private static final long serialVersionUID = 1L;

	private String serviceName = "natural-language-understanding";

	// If running locally complete the variables below
	// (in the config.properties file).
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
	 *            the Http Servlet response
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

		try {
			SimpleNLUClient client = new SimpleNLUClient();
			if (useIamApiKey) {
				logger.info("create NLU service using apikey");
				logger.info("   apikey: " + apikey);
				logger.info("   endpoint: " + baseURL);
				client.initIamService(apikey, baseURL);
			} else {
				logger.info("create NLU service using uname/pwd");
				logger.info("   username: " + username);
				logger.info("   password: " + password);
				logger.info("   endpoint: " + baseURL);
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
	 * Value is considered entered if it is set, and doesn't equal the placeholder value
	 */
	private boolean keyValueEntered(String key, String value) {
		if ((value != null) && (!value.isEmpty()) && (!value.startsWith("<add_"))) {
			logger.info(key + " has been set to: " + value);
			return true;
		}
		logger.info(key + " has NOT been set");
		return false;
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
		} catch (Exception e) {
			logger.log(Level.SEVERE, "Reading config properties error: " + e.getMessage(), e);
			return false;
		}

		// make sure we have what we need

		// always need model id
		if (!keyValueEntered("config-modelId", modelId)) {
			return false;
		}

		// always need nlu url
		if (!keyValueEntered("config-baseURL", baseURL)) {
			return false;
		}

		// need at least one type of credential
		if (!keyValueEntered("config-apikey", apikey) &&
			(!keyValueEntered("config-uname", username) || (!keyValueEntered("config-pwd", password)))) {
			return false;
		}

		if (keyValueEntered("config-apikey", apikey)) {
			useIamApiKey = true;
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
				logger.info("VCAP: " + credentials);
				baseURL = (String) credentials.get("url");
				apikey = (String) credentials.get("apikey");
				username = (String) credentials.get("username");
				password = (String) credentials.get("password");
				if (keyValueEntered("vcap-apikey", apikey)) {
					useIamApiKey = true;
				}
				logger.info("vcap-baseURL  = " + baseURL);
				logger.info("vcap-apikey = " + apikey);
				logger.info("vcap-username = " + username);
				logger.info("vcap-password = " + password);
				logger.info("vcap-using apikey = " + useIamApiKey);
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
