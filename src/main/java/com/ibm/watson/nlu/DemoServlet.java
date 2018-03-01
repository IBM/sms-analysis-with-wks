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
import java.net.URI;
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
	private String modelId = "";

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

		// create the request
		String text = req.getParameter("text");
		String language = req.getParameter("language");
		String locale = req.getLocale().toString().replace("_", "-");

		try {
			SimpleNLUClient client = new SimpleNLUClient();
			client.initService(username, password, "");
			AnalysisResults response = client.analyze(modelId,text);
			resp.getWriter().println(response);
			//resp.getWriter().println("{ \"language\": \"en\", \"entities\": [ { \"type\": \"Merchant\", \"text\": \"DUNKI DONUTS\", \"count\": 1 }, { \"type\": \"Location\", \"text\": \"Girgaum\", \"count\": 1 },"+
			//" { \"type\": \"Offer\", \"text\": \"Get 3 FREE\", \"count\": 1 }, { \"type\": \"Offer_Period\", \"text\": \"Valid till 15 Feb 2017\", \"count\": 1 }, { \"type\": \"Term_and_Conditions\", \"text\": \"T&C\", \"count\": 1 } ] }");

		} catch (Exception e) {
		    // Don't throw the error. Stuff it in an "Error" entity so the user can see it.
			logger.log(Level.SEVERE, "Service error: " + e.getMessage(), e);
			resp.getWriter().println("{ \"language\": \"en\", \"entities\": [ { \"type\": \"Error\", \"text\": \"" +
					e.toString() + "\", \"count\": 1 } ] }");
		}
	}

	@Override
	public void init() throws ServletException {
		super.init();
		processVCAPServices();
		modelId = System.getenv("modelId");
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
				username = (String) credentials.get("username");
				password = (String) credentials.get("password");
				logger.info("baseURL  = " + baseURL);
				logger.info("username = " + username);
				logger.info("password = " + password);
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
