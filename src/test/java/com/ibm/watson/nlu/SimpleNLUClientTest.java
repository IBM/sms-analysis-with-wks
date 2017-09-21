/**
* Copyright 2017 IBM Corp. All Rights Reserved.
*
* Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
* the License. You may obtain a copy of the License at
*
* http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
* an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
* specific language governing permissions and limitations under the License.
*/
package com.ibm.watson.nlu;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.ibm.watson.developer_cloud.WatsonServiceUnitTest;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.AnalysisResults;

public class SimpleNLUClientTest extends WatsonServiceUnitTest {

private SimpleNLUClient service;
private static final String RESOURCE = "src/test/resources/";
private AnalysisResults analyzeResults1;
private AnalysisResults analyzeResults2;
private AnalysisResults analyzeResults3;

@Override
@Before
public void setUp() throws Exception {
  super.setUp();
  service = new SimpleNLUClient();
  service.initService("dummyusername","dummypassword",getMockWebServerUrl());
  analyzeResults1 = loadFixture(RESOURCE + "analyze1.json", AnalysisResults.class);
  analyzeResults2 = loadFixture(RESOURCE + "analyze2.json", AnalysisResults.class);
  analyzeResults2 = loadFixture(RESOURCE + "analyze3.json", AnalysisResults.class);
}

/**
* Test analyze.
*
* @throws InterruptedException the interrupted exception
* @throws FileNotFoundException
*/
@Test
public void testAnalyzeSampleFormat1() throws InterruptedException, FileNotFoundException {
  String testSmsFileName = RESOURCE + "testsms1.txt";
  String sms = getStringFromInputStream(new FileInputStream(testSmsFileName));
  server.enqueue(jsonResponse(analyzeResults1));

  final AnalysisResults response = service.analyze("",sms);
  assertEquals(analyzeResults1, response);
}
/**
* Test analyze.
*
* @throws InterruptedException the interrupted exception
* @throws FileNotFoundException
*/
@Test
public void testAnalyzeSampleFormat2() throws InterruptedException, FileNotFoundException {
  String testSmsFileName = RESOURCE + "testsms2.txt";
  String sms = getStringFromInputStream(new FileInputStream(testSmsFileName));
  server.enqueue(jsonResponse(analyzeResults2));

  final AnalysisResults response = service.analyze("",sms);
  assertEquals(analyzeResults2, response);
}

/**
* Test analyze.
*
* @throws InterruptedException the interrupted exception
* @throws FileNotFoundException
*/
@Test
public void testAnalyzeSampleFormat1WithWKS() throws InterruptedException, FileNotFoundException {
  String testSmsFileName = RESOURCE + "testsms1.txt";
  String sms = getStringFromInputStream(new FileInputStream(testSmsFileName));
  server.enqueue(jsonResponse(analyzeResults1));

  final AnalysisResults response = service.analyze("",sms);
  assertEquals(analyzeResults1, response);
}

/**
* Test analyze.
*
* @throws InterruptedException the interrupted exception
* @throws FileNotFoundException
*/
@Test
public void testAnalyzeSampleFormat2WithWKS() throws InterruptedException, FileNotFoundException {
  String testSmsFileName = RESOURCE + "testsms2.txt";
  String sms = getStringFromInputStream(new FileInputStream(testSmsFileName));
  server.enqueue(jsonResponse(analyzeResults2));

  final AnalysisResults response = service.analyze("",sms);
  assertEquals(analyzeResults2, response);
}

/**
* Test analyze.
*
* @throws InterruptedException the interrupted exception
* @throws FileNotFoundException
*/
@Test
public void testAnalyzeEmptySmsWithWKS() throws InterruptedException, FileNotFoundException {

  server.enqueue(jsonResponse(analyzeResults3));

  final AnalysisResults response = service.analyze("","");
  assertEquals(analyzeResults3, response);
}
}
