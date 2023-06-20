/*
 * (C) Copyright IBM Corp. 2023.
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
package com.ibm.cloud.container_registry.container_registry.v1;

import com.ibm.cloud.container_registry.container_registry.v1.ContainerRegistry;
import com.ibm.cloud.container_registry.container_registry.v1.model.AccountSettings;
import com.ibm.cloud.container_registry.container_registry.v1.model.AnalyzeRetentionPolicyOptions;
import com.ibm.cloud.container_registry.container_registry.v1.model.AssignNamespaceOptions;
import com.ibm.cloud.container_registry.container_registry.v1.model.AuthOptions;
import com.ibm.cloud.container_registry.container_registry.v1.model.BulkDeleteImagesOptions;
import com.ibm.cloud.container_registry.container_registry.v1.model.Config;
import com.ibm.cloud.container_registry.container_registry.v1.model.CreateNamespaceOptions;
import com.ibm.cloud.container_registry.container_registry.v1.model.DeleteImageOptions;
import com.ibm.cloud.container_registry.container_registry.v1.model.DeleteImageTagOptions;
import com.ibm.cloud.container_registry.container_registry.v1.model.DeleteNamespaceOptions;
import com.ibm.cloud.container_registry.container_registry.v1.model.GetAuthOptions;
import com.ibm.cloud.container_registry.container_registry.v1.model.GetImageManifestOptions;
import com.ibm.cloud.container_registry.container_registry.v1.model.GetMessagesOptions;
import com.ibm.cloud.container_registry.container_registry.v1.model.GetPlansOptions;
import com.ibm.cloud.container_registry.container_registry.v1.model.GetQuotaOptions;
import com.ibm.cloud.container_registry.container_registry.v1.model.GetRetentionPolicyOptions;
import com.ibm.cloud.container_registry.container_registry.v1.model.GetSettingsOptions;
import com.ibm.cloud.container_registry.container_registry.v1.model.HealthConfig;
import com.ibm.cloud.container_registry.container_registry.v1.model.ImageBulkDeleteError;
import com.ibm.cloud.container_registry.container_registry.v1.model.ImageBulkDeleteResult;
import com.ibm.cloud.container_registry.container_registry.v1.model.ImageDeleteResult;
import com.ibm.cloud.container_registry.container_registry.v1.model.ImageDigest;
import com.ibm.cloud.container_registry.container_registry.v1.model.ImageInspection;
import com.ibm.cloud.container_registry.container_registry.v1.model.InspectImageOptions;
import com.ibm.cloud.container_registry.container_registry.v1.model.ListDeletedImagesOptions;
import com.ibm.cloud.container_registry.container_registry.v1.model.ListImageDigestsOptions;
import com.ibm.cloud.container_registry.container_registry.v1.model.ListImagesOptions;
import com.ibm.cloud.container_registry.container_registry.v1.model.ListNamespaceDetailsOptions;
import com.ibm.cloud.container_registry.container_registry.v1.model.ListNamespacesOptions;
import com.ibm.cloud.container_registry.container_registry.v1.model.ListRetentionPoliciesOptions;
import com.ibm.cloud.container_registry.container_registry.v1.model.Namespace;
import com.ibm.cloud.container_registry.container_registry.v1.model.NamespaceDetails;
import com.ibm.cloud.container_registry.container_registry.v1.model.Plan;
import com.ibm.cloud.container_registry.container_registry.v1.model.Quota;
import com.ibm.cloud.container_registry.container_registry.v1.model.QuotaDetails;
import com.ibm.cloud.container_registry.container_registry.v1.model.RemoteAPIImage;
import com.ibm.cloud.container_registry.container_registry.v1.model.RestoreImageOptions;
import com.ibm.cloud.container_registry.container_registry.v1.model.RestoreResult;
import com.ibm.cloud.container_registry.container_registry.v1.model.RestoreTagsOptions;
import com.ibm.cloud.container_registry.container_registry.v1.model.RetentionPolicy;
import com.ibm.cloud.container_registry.container_registry.v1.model.RootFS;
import com.ibm.cloud.container_registry.container_registry.v1.model.SetRetentionPolicyOptions;
import com.ibm.cloud.container_registry.container_registry.v1.model.TagImageOptions;
import com.ibm.cloud.container_registry.container_registry.v1.model.Trash;
import com.ibm.cloud.container_registry.container_registry.v1.model.UpdateAuthOptions;
import com.ibm.cloud.container_registry.container_registry.v1.model.UpdatePlansOptions;
import com.ibm.cloud.container_registry.container_registry.v1.model.UpdateQuotaOptions;
import com.ibm.cloud.container_registry.container_registry.v1.model.UpdateSettingsOptions;
import com.ibm.cloud.container_registry.container_registry.v1.model.VAReport;
import com.ibm.cloud.container_registry.container_registry.v1.utils.TestUtilities;
import com.ibm.cloud.sdk.core.http.Response;
import com.ibm.cloud.sdk.core.security.Authenticator;
import com.ibm.cloud.sdk.core.security.NoAuthAuthenticator;
import com.ibm.cloud.sdk.core.service.model.FileWithMetadata;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static org.testng.Assert.*;

/**
 * Unit test class for the ContainerRegistry service.
 */
public class ContainerRegistryTest {

  final HashMap<String, InputStream> mockStreamMap = TestUtilities.createMockStreamMap();
  final List<FileWithMetadata> mockListFileWithMetadata = TestUtilities.creatMockListFileWithMetadata();

  protected MockWebServer server;
  protected ContainerRegistry containerRegistryService;

  // Construct the service with a null authenticator (negative test)
  @Test(expectedExceptions = IllegalArgumentException.class)
  public void testConstructorWithNullAuthenticator() throws Throwable {
    final String serviceName = "testService";
    // Set mock values for global params
    String account = "testString";
    new ContainerRegistry(account, serviceName, null);
  }

  // Test the getServiceUrlForRegion() method
  @Test
  public void testGetServiceUrlForRegion() throws Throwable {
    assertNull(ContainerRegistry.getServiceUrlForRegion("INVALID_REGION"));
    assertEquals(ContainerRegistry.getServiceUrlForRegion("global"), "https://icr.io");
    assertEquals(ContainerRegistry.getServiceUrlForRegion("us-south"), "https://us.icr.io");
    assertEquals(ContainerRegistry.getServiceUrlForRegion("uk-south"), "https://uk.icr.io");
    assertEquals(ContainerRegistry.getServiceUrlForRegion("eu-gb"), "https://uk.icr.io");
    assertEquals(ContainerRegistry.getServiceUrlForRegion("eu-central"), "https://de.icr.io");
    assertEquals(ContainerRegistry.getServiceUrlForRegion("eu-de"), "https://de.icr.io");
    assertEquals(ContainerRegistry.getServiceUrlForRegion("ap-north"), "https://jp.icr.io");
    assertEquals(ContainerRegistry.getServiceUrlForRegion("jp-tok"), "https://jp.icr.io");
    assertEquals(ContainerRegistry.getServiceUrlForRegion("ap-south"), "https://au.icr.io");
    assertEquals(ContainerRegistry.getServiceUrlForRegion("au-syd"), "https://au.icr.io");
    assertEquals(ContainerRegistry.getServiceUrlForRegion("jp-osa"), "https://jp2.icr.io");
    assertEquals(ContainerRegistry.getServiceUrlForRegion("ca-tor"), "https://ca.icr.io");
    assertEquals(ContainerRegistry.getServiceUrlForRegion("br-sao"), "https://br.icr.io");
  }


  // Test the getter for the account global parameter
  @Test
  public void testGetAccount() throws Throwable {
    assertEquals(containerRegistryService.getAccount(), "testString");
  }

  // Test the getAuth operation with a valid options model parameter
  @Test
  public void testGetAuthWOptions() throws Throwable {
    // Register a mock response
    String mockResponseBody = "{\"iam_authz\": true, \"private_only\": false}";
    String getAuthPath = "/api/v1/auth";
    server.enqueue(new MockResponse()
      .setHeader("Content-type", "application/json")
      .setResponseCode(200)
      .setBody(mockResponseBody));

    // Construct an instance of the GetAuthOptions model
    GetAuthOptions getAuthOptionsModel = new GetAuthOptions();

    // Invoke getAuth() with a valid options model and verify the result
    Response<AuthOptions> response = containerRegistryService.getAuth(getAuthOptionsModel).execute();
    assertNotNull(response);
    AuthOptions responseObj = response.getResult();
    assertNotNull(responseObj);

    // Verify the contents of the request sent to the mock server
    RecordedRequest request = server.takeRequest();
    assertNotNull(request);
    assertEquals(request.getMethod(), "GET");
    // Verify request path
    String parsedPath = TestUtilities.parseReqPath(request);
    assertEquals(parsedPath, getAuthPath);
    // Verify header parameters
    assertEquals(request.getHeader("Account"), "testString");
    // Verify that there is no query string
    Map<String, String> query = TestUtilities.parseQueryString(request);
    assertNull(query);
  }

  // Test the getAuth operation with and without retries enabled
  @Test
  public void testGetAuthWRetries() throws Throwable {
    containerRegistryService.enableRetries(4, 30);
    testGetAuthWOptions();

    containerRegistryService.disableRetries();
    testGetAuthWOptions();
  }

  // Test the updateAuth operation with a valid options model parameter
  @Test
  public void testUpdateAuthWOptions() throws Throwable {
    // Register a mock response
    String mockResponseBody = "";
    String updateAuthPath = "/api/v1/auth";
    server.enqueue(new MockResponse()
      .setResponseCode(204)
      .setBody(mockResponseBody));

    // Construct an instance of the UpdateAuthOptions model
    UpdateAuthOptions updateAuthOptionsModel = new UpdateAuthOptions.Builder()
      .iamAuthz(true)
      .privateOnly(true)
      .build();

    // Invoke updateAuth() with a valid options model and verify the result
    Response<Void> response = containerRegistryService.updateAuth(updateAuthOptionsModel).execute();
    assertNotNull(response);
    Void responseObj = response.getResult();
    assertNull(responseObj);

    // Verify the contents of the request sent to the mock server
    RecordedRequest request = server.takeRequest();
    assertNotNull(request);
    assertEquals(request.getMethod(), "PATCH");
    // Verify request path
    String parsedPath = TestUtilities.parseReqPath(request);
    assertEquals(parsedPath, updateAuthPath);
    // Verify header parameters
    assertEquals(request.getHeader("Account"), "testString");
    // Verify that there is no query string
    Map<String, String> query = TestUtilities.parseQueryString(request);
    assertNull(query);
  }

  // Test the updateAuth operation with and without retries enabled
  @Test
  public void testUpdateAuthWRetries() throws Throwable {
    containerRegistryService.enableRetries(4, 30);
    testUpdateAuthWOptions();

    containerRegistryService.disableRetries();
    testUpdateAuthWOptions();
  }

  // Test the listImages operation with a valid options model parameter
  @Test
  public void testListImagesWOptions() throws Throwable {
    // Register a mock response
    String mockResponseBody = "[{\"ConfigurationIssueCount\": 23, \"Created\": 7, \"DigestTags\": {\"mapKey\": [\"inner\"]}, \"ExemptIssueCount\": 16, \"Id\": \"id\", \"IssueCount\": 10, \"Labels\": {\"mapKey\": \"inner\"}, \"ManifestType\": \"manifestType\", \"ParentId\": \"parentId\", \"RepoDigests\": [\"repoDigests\"], \"RepoTags\": [\"repoTags\"], \"Size\": 4, \"VirtualSize\": 11, \"VulnerabilityCount\": 18, \"Vulnerable\": \"vulnerable\"}]";
    String listImagesPath = "/api/v1/images";
    server.enqueue(new MockResponse()
      .setHeader("Content-type", "application/json")
      .setResponseCode(200)
      .setBody(mockResponseBody));

    // Construct an instance of the ListImagesOptions model
    ListImagesOptions listImagesOptionsModel = new ListImagesOptions.Builder()
      .namespace("testString")
      .includeIbm(true)
      .includePrivate(true)
      .includeManifestLists(true)
      .vulnerabilities(true)
      .repository("testString")
      .build();

    // Invoke listImages() with a valid options model and verify the result
    Response<List<RemoteAPIImage>> response = containerRegistryService.listImages(listImagesOptionsModel).execute();
    assertNotNull(response);
    List<RemoteAPIImage> responseObj = response.getResult();
    assertNotNull(responseObj);

    // Verify the contents of the request sent to the mock server
    RecordedRequest request = server.takeRequest();
    assertNotNull(request);
    assertEquals(request.getMethod(), "GET");
    // Verify request path
    String parsedPath = TestUtilities.parseReqPath(request);
    assertEquals(parsedPath, listImagesPath);
    // Verify header parameters
    assertEquals(request.getHeader("Account"), "testString");
    // Verify query params
    Map<String, String> query = TestUtilities.parseQueryString(request);
    assertNotNull(query);
    assertEquals(query.get("namespace"), "testString");
    assertEquals(Boolean.valueOf(query.get("includeIBM")), Boolean.valueOf(true));
    assertEquals(Boolean.valueOf(query.get("includePrivate")), Boolean.valueOf(true));
    assertEquals(Boolean.valueOf(query.get("includeManifestLists")), Boolean.valueOf(true));
    assertEquals(Boolean.valueOf(query.get("vulnerabilities")), Boolean.valueOf(true));
    assertEquals(query.get("repository"), "testString");
  }

  // Test the listImages operation with and without retries enabled
  @Test
  public void testListImagesWRetries() throws Throwable {
    containerRegistryService.enableRetries(4, 30);
    testListImagesWOptions();

    containerRegistryService.disableRetries();
    testListImagesWOptions();
  }

  // Test the bulkDeleteImages operation with a valid options model parameter
  @Test
  public void testBulkDeleteImagesWOptions() throws Throwable {
    // Register a mock response
    String mockResponseBody = "{\"error\": {\"mapKey\": {\"code\": \"code\", \"message\": \"message\"}}, \"success\": [\"success\"]}";
    String bulkDeleteImagesPath = "/api/v1/images/bulkdelete";
    server.enqueue(new MockResponse()
      .setHeader("Content-type", "application/json")
      .setResponseCode(200)
      .setBody(mockResponseBody));

    // Construct an instance of the BulkDeleteImagesOptions model
    BulkDeleteImagesOptions bulkDeleteImagesOptionsModel = new BulkDeleteImagesOptions.Builder()
      .bulkDelete(java.util.Arrays.asList("us.icr.io/birds/woodpecker@sha256:38f97dd92769b18ca82ad9ab6667af47306e66fea5b446937eea68b10ab4bbbb", "us.icr.io/birds/bird@sha256:38f97dd92769b18ca82ad9ab6667af47306e66fea5b446937eea68b10ab4dddd"))
      .build();

    // Invoke bulkDeleteImages() with a valid options model and verify the result
    Response<ImageBulkDeleteResult> response = containerRegistryService.bulkDeleteImages(bulkDeleteImagesOptionsModel).execute();
    assertNotNull(response);
    ImageBulkDeleteResult responseObj = response.getResult();
    assertNotNull(responseObj);

    // Verify the contents of the request sent to the mock server
    RecordedRequest request = server.takeRequest();
    assertNotNull(request);
    assertEquals(request.getMethod(), "POST");
    // Verify request path
    String parsedPath = TestUtilities.parseReqPath(request);
    assertEquals(parsedPath, bulkDeleteImagesPath);
    // Verify header parameters
    assertEquals(request.getHeader("Account"), "testString");
    // Verify that there is no query string
    Map<String, String> query = TestUtilities.parseQueryString(request);
    assertNull(query);
  }

  // Test the bulkDeleteImages operation with and without retries enabled
  @Test
  public void testBulkDeleteImagesWRetries() throws Throwable {
    containerRegistryService.enableRetries(4, 30);
    testBulkDeleteImagesWOptions();

    containerRegistryService.disableRetries();
    testBulkDeleteImagesWOptions();
  }

  // Test the bulkDeleteImages operation with a null options model (negative test)
  @Test(expectedExceptions = IllegalArgumentException.class)
  public void testBulkDeleteImagesNoOptions() throws Throwable {
    server.enqueue(new MockResponse());
    containerRegistryService.bulkDeleteImages(null).execute();
  }

  // Test the listImageDigests operation with a valid options model parameter
  @Test
  public void testListImageDigestsWOptions() throws Throwable {
    // Register a mock response
    String mockResponseBody = "[{\"created\": 7, \"id\": \"id\", \"manifestType\": \"manifestType\", \"repoTags\": {\"mapKey\": {\"mapKey\": {\"configurationIssueCount\": 23, \"exemptIssueCount\": 16, \"issueCount\": 10, \"vulnerabilityCount\": 18, \"vulnerable\": \"vulnerable\"}}}, \"size\": 4}]";
    String listImageDigestsPath = "/api/v1/images/digests";
    server.enqueue(new MockResponse()
      .setHeader("Content-type", "application/json")
      .setResponseCode(200)
      .setBody(mockResponseBody));

    // Construct an instance of the ListImageDigestsOptions model
    ListImageDigestsOptions listImageDigestsOptionsModel = new ListImageDigestsOptions.Builder()
      .excludeTagged(false)
      .excludeVa(false)
      .includeIbm(false)
      .repositories(java.util.Arrays.asList("testString"))
      .build();

    // Invoke listImageDigests() with a valid options model and verify the result
    Response<List<ImageDigest>> response = containerRegistryService.listImageDigests(listImageDigestsOptionsModel).execute();
    assertNotNull(response);
    List<ImageDigest> responseObj = response.getResult();
    assertNotNull(responseObj);

    // Verify the contents of the request sent to the mock server
    RecordedRequest request = server.takeRequest();
    assertNotNull(request);
    assertEquals(request.getMethod(), "POST");
    // Verify request path
    String parsedPath = TestUtilities.parseReqPath(request);
    assertEquals(parsedPath, listImageDigestsPath);
    // Verify header parameters
    assertEquals(request.getHeader("Account"), "testString");
    // Verify that there is no query string
    Map<String, String> query = TestUtilities.parseQueryString(request);
    assertNull(query);
  }

  // Test the listImageDigests operation with and without retries enabled
  @Test
  public void testListImageDigestsWRetries() throws Throwable {
    containerRegistryService.enableRetries(4, 30);
    testListImageDigestsWOptions();

    containerRegistryService.disableRetries();
    testListImageDigestsWOptions();
  }

  // Test the tagImage operation with a valid options model parameter
  @Test
  public void testTagImageWOptions() throws Throwable {
    // Register a mock response
    String mockResponseBody = "";
    String tagImagePath = "/api/v1/images/tags";
    server.enqueue(new MockResponse()
      .setResponseCode(201)
      .setBody(mockResponseBody));

    // Construct an instance of the TagImageOptions model
    TagImageOptions tagImageOptionsModel = new TagImageOptions.Builder()
      .fromimage("testString")
      .toimage("testString")
      .build();

    // Invoke tagImage() with a valid options model and verify the result
    Response<Void> response = containerRegistryService.tagImage(tagImageOptionsModel).execute();
    assertNotNull(response);
    Void responseObj = response.getResult();
    assertNull(responseObj);

    // Verify the contents of the request sent to the mock server
    RecordedRequest request = server.takeRequest();
    assertNotNull(request);
    assertEquals(request.getMethod(), "POST");
    // Verify request path
    String parsedPath = TestUtilities.parseReqPath(request);
    assertEquals(parsedPath, tagImagePath);
    // Verify header parameters
    assertEquals(request.getHeader("Account"), "testString");
    // Verify query params
    Map<String, String> query = TestUtilities.parseQueryString(request);
    assertNotNull(query);
    assertEquals(query.get("fromimage"), "testString");
    assertEquals(query.get("toimage"), "testString");
  }

  // Test the tagImage operation with and without retries enabled
  @Test
  public void testTagImageWRetries() throws Throwable {
    containerRegistryService.enableRetries(4, 30);
    testTagImageWOptions();

    containerRegistryService.disableRetries();
    testTagImageWOptions();
  }

  // Test the tagImage operation with a null options model (negative test)
  @Test(expectedExceptions = IllegalArgumentException.class)
  public void testTagImageNoOptions() throws Throwable {
    server.enqueue(new MockResponse());
    containerRegistryService.tagImage(null).execute();
  }

  // Test the deleteImage operation with a valid options model parameter
  @Test
  public void testDeleteImageWOptions() throws Throwable {
    // Register a mock response
    String mockResponseBody = "{\"Untagged\": \"untagged\"}";
    String deleteImagePath = "/api/v1/images/testString";
    server.enqueue(new MockResponse()
      .setHeader("Content-type", "application/json")
      .setResponseCode(200)
      .setBody(mockResponseBody));

    // Construct an instance of the DeleteImageOptions model
    DeleteImageOptions deleteImageOptionsModel = new DeleteImageOptions.Builder()
      .image("testString")
      .build();

    // Invoke deleteImage() with a valid options model and verify the result
    Response<ImageDeleteResult> response = containerRegistryService.deleteImage(deleteImageOptionsModel).execute();
    assertNotNull(response);
    ImageDeleteResult responseObj = response.getResult();
    assertNotNull(responseObj);

    // Verify the contents of the request sent to the mock server
    RecordedRequest request = server.takeRequest();
    assertNotNull(request);
    assertEquals(request.getMethod(), "DELETE");
    // Verify request path
    String parsedPath = TestUtilities.parseReqPath(request);
    assertEquals(parsedPath, deleteImagePath);
    // Verify header parameters
    assertEquals(request.getHeader("Account"), "testString");
    // Verify that there is no query string
    Map<String, String> query = TestUtilities.parseQueryString(request);
    assertNull(query);
  }

  // Test the deleteImage operation with and without retries enabled
  @Test
  public void testDeleteImageWRetries() throws Throwable {
    containerRegistryService.enableRetries(4, 30);
    testDeleteImageWOptions();

    containerRegistryService.disableRetries();
    testDeleteImageWOptions();
  }

  // Test the deleteImage operation with a null options model (negative test)
  @Test(expectedExceptions = IllegalArgumentException.class)
  public void testDeleteImageNoOptions() throws Throwable {
    server.enqueue(new MockResponse());
    containerRegistryService.deleteImage(null).execute();
  }

  // Test the inspectImage operation with a valid options model parameter
  @Test
  public void testInspectImageWOptions() throws Throwable {
    // Register a mock response
    String mockResponseBody = "{\"Architecture\": \"architecture\", \"Author\": \"author\", \"Comment\": \"comment\", \"Config\": {\"ArgsEscaped\": false, \"AttachStderr\": true, \"AttachStdin\": false, \"AttachStdout\": true, \"Cmd\": [\"cmd\"], \"Domainname\": \"domainname\", \"Entrypoint\": [\"entrypoint\"], \"Env\": [\"env\"], \"ExposedPorts\": {\"anyKey\": \"anyValue\"}, \"Healthcheck\": {\"Interval\": 8, \"Retries\": 7, \"Test\": [\"test\"], \"Timeout\": 7}, \"Hostname\": \"hostname\", \"Image\": \"image\", \"Labels\": {\"mapKey\": \"inner\"}, \"MacAddress\": \"macAddress\", \"NetworkDisabled\": false, \"OnBuild\": [\"onBuild\"], \"OpenStdin\": false, \"Shell\": [\"shell\"], \"StdinOnce\": false, \"StopSignal\": \"stopSignal\", \"StopTimeout\": 11, \"Tty\": false, \"User\": \"user\", \"Volumes\": {\"anyKey\": \"anyValue\"}, \"WorkingDir\": \"workingDir\"}, \"Container\": \"container\", \"ContainerConfig\": {\"ArgsEscaped\": false, \"AttachStderr\": true, \"AttachStdin\": false, \"AttachStdout\": true, \"Cmd\": [\"cmd\"], \"Domainname\": \"domainname\", \"Entrypoint\": [\"entrypoint\"], \"Env\": [\"env\"], \"ExposedPorts\": {\"anyKey\": \"anyValue\"}, \"Healthcheck\": {\"Interval\": 8, \"Retries\": 7, \"Test\": [\"test\"], \"Timeout\": 7}, \"Hostname\": \"hostname\", \"Image\": \"image\", \"Labels\": {\"mapKey\": \"inner\"}, \"MacAddress\": \"macAddress\", \"NetworkDisabled\": false, \"OnBuild\": [\"onBuild\"], \"OpenStdin\": false, \"Shell\": [\"shell\"], \"StdinOnce\": false, \"StopSignal\": \"stopSignal\", \"StopTimeout\": 11, \"Tty\": false, \"User\": \"user\", \"Volumes\": {\"anyKey\": \"anyValue\"}, \"WorkingDir\": \"workingDir\"}, \"Created\": \"created\", \"DockerVersion\": \"dockerVersion\", \"Id\": \"id\", \"ManifestType\": \"manifestType\", \"Os\": \"os\", \"OsVersion\": \"osVersion\", \"Parent\": \"parent\", \"RootFS\": {\"BaseLayer\": \"baseLayer\", \"Layers\": [\"layers\"], \"Type\": \"type\"}, \"Size\": 4, \"VirtualSize\": 11}";
    String inspectImagePath = "/api/v1/images/testString/json";
    server.enqueue(new MockResponse()
      .setHeader("Content-type", "application/json")
      .setResponseCode(200)
      .setBody(mockResponseBody));

    // Construct an instance of the InspectImageOptions model
    InspectImageOptions inspectImageOptionsModel = new InspectImageOptions.Builder()
      .image("testString")
      .build();

    // Invoke inspectImage() with a valid options model and verify the result
    Response<ImageInspection> response = containerRegistryService.inspectImage(inspectImageOptionsModel).execute();
    assertNotNull(response);
    ImageInspection responseObj = response.getResult();
    assertNotNull(responseObj);

    // Verify the contents of the request sent to the mock server
    RecordedRequest request = server.takeRequest();
    assertNotNull(request);
    assertEquals(request.getMethod(), "GET");
    // Verify request path
    String parsedPath = TestUtilities.parseReqPath(request);
    assertEquals(parsedPath, inspectImagePath);
    // Verify header parameters
    assertEquals(request.getHeader("Account"), "testString");
    // Verify that there is no query string
    Map<String, String> query = TestUtilities.parseQueryString(request);
    assertNull(query);
  }

  // Test the inspectImage operation with and without retries enabled
  @Test
  public void testInspectImageWRetries() throws Throwable {
    containerRegistryService.enableRetries(4, 30);
    testInspectImageWOptions();

    containerRegistryService.disableRetries();
    testInspectImageWOptions();
  }

  // Test the inspectImage operation with a null options model (negative test)
  @Test(expectedExceptions = IllegalArgumentException.class)
  public void testInspectImageNoOptions() throws Throwable {
    server.enqueue(new MockResponse());
    containerRegistryService.inspectImage(null).execute();
  }

  // Test the getImageManifest operation with a valid options model parameter
  @Test
  public void testGetImageManifestWOptions() throws Throwable {
    // Register a mock response
    String mockResponseBody = "{\"anyKey\": \"anyValue\"}";
    String getImageManifestPath = "/api/v1/images/testString/manifest";
    server.enqueue(new MockResponse()
      .setHeader("Content-type", "application/json")
      .setResponseCode(200)
      .setBody(mockResponseBody));

    // Construct an instance of the GetImageManifestOptions model
    GetImageManifestOptions getImageManifestOptionsModel = new GetImageManifestOptions.Builder()
      .image("testString")
      .build();

    // Invoke getImageManifest() with a valid options model and verify the result
    Response<Map<String, Object>> response = containerRegistryService.getImageManifest(getImageManifestOptionsModel).execute();
    assertNotNull(response);
    Map<String, Object> responseObj = response.getResult();
    assertNotNull(responseObj);

    // Verify the contents of the request sent to the mock server
    RecordedRequest request = server.takeRequest();
    assertNotNull(request);
    assertEquals(request.getMethod(), "GET");
    // Verify request path
    String parsedPath = TestUtilities.parseReqPath(request);
    assertEquals(parsedPath, getImageManifestPath);
    // Verify header parameters
    assertEquals(request.getHeader("Account"), "testString");
    // Verify that there is no query string
    Map<String, String> query = TestUtilities.parseQueryString(request);
    assertNull(query);
  }

  // Test the getImageManifest operation with and without retries enabled
  @Test
  public void testGetImageManifestWRetries() throws Throwable {
    containerRegistryService.enableRetries(4, 30);
    testGetImageManifestWOptions();

    containerRegistryService.disableRetries();
    testGetImageManifestWOptions();
  }

  // Test the getImageManifest operation with a null options model (negative test)
  @Test(expectedExceptions = IllegalArgumentException.class)
  public void testGetImageManifestNoOptions() throws Throwable {
    server.enqueue(new MockResponse());
    containerRegistryService.getImageManifest(null).execute();
  }

  // Test the getMessages operation with a valid options model parameter
  @Test
  public void testGetMessagesWOptions() throws Throwable {
    // Register a mock response
    String mockResponseBody = "\"Hello, world!\"";
    String getMessagesPath = "/api/v1/messages";
    server.enqueue(new MockResponse()
      .setHeader("Content-type", "application/json")
      .setResponseCode(200)
      .setBody(mockResponseBody));

    // Construct an instance of the GetMessagesOptions model
    GetMessagesOptions getMessagesOptionsModel = new GetMessagesOptions();

    // Invoke getMessages() with a valid options model and verify the result
    Response<String> response = containerRegistryService.getMessages(getMessagesOptionsModel).execute();
    assertNotNull(response);
    String responseObj = response.getResult();
    assertNotNull(responseObj);

    // Verify the contents of the request sent to the mock server
    RecordedRequest request = server.takeRequest();
    assertNotNull(request);
    assertEquals(request.getMethod(), "GET");
    // Verify request path
    String parsedPath = TestUtilities.parseReqPath(request);
    assertEquals(parsedPath, getMessagesPath);
    // Verify that there is no query string
    Map<String, String> query = TestUtilities.parseQueryString(request);
    assertNull(query);
  }

  // Test the getMessages operation with and without retries enabled
  @Test
  public void testGetMessagesWRetries() throws Throwable {
    containerRegistryService.enableRetries(4, 30);
    testGetMessagesWOptions();

    containerRegistryService.disableRetries();
    testGetMessagesWOptions();
  }

  // Test the listNamespaces operation with a valid options model parameter
  @Test
  public void testListNamespacesWOptions() throws Throwable {
    // Register a mock response
    String mockResponseBody = "[\"operationResponse\"]";
    String listNamespacesPath = "/api/v1/namespaces";
    server.enqueue(new MockResponse()
      .setHeader("Content-type", "application/json")
      .setResponseCode(200)
      .setBody(mockResponseBody));

    // Construct an instance of the ListNamespacesOptions model
    ListNamespacesOptions listNamespacesOptionsModel = new ListNamespacesOptions();

    // Invoke listNamespaces() with a valid options model and verify the result
    Response<List<String>> response = containerRegistryService.listNamespaces(listNamespacesOptionsModel).execute();
    assertNotNull(response);
    List<String> responseObj = response.getResult();
    assertNotNull(responseObj);

    // Verify the contents of the request sent to the mock server
    RecordedRequest request = server.takeRequest();
    assertNotNull(request);
    assertEquals(request.getMethod(), "GET");
    // Verify request path
    String parsedPath = TestUtilities.parseReqPath(request);
    assertEquals(parsedPath, listNamespacesPath);
    // Verify header parameters
    assertEquals(request.getHeader("Account"), "testString");
    // Verify that there is no query string
    Map<String, String> query = TestUtilities.parseQueryString(request);
    assertNull(query);
  }

  // Test the listNamespaces operation with and without retries enabled
  @Test
  public void testListNamespacesWRetries() throws Throwable {
    containerRegistryService.enableRetries(4, 30);
    testListNamespacesWOptions();

    containerRegistryService.disableRetries();
    testListNamespacesWOptions();
  }

  // Test the listNamespaceDetails operation with a valid options model parameter
  @Test
  public void testListNamespaceDetailsWOptions() throws Throwable {
    // Register a mock response
    String mockResponseBody = "[{\"account\": \"account\", \"created_date\": \"createdDate\", \"crn\": \"crn\", \"name\": \"name\", \"resource_created_date\": \"resourceCreatedDate\", \"resource_group\": \"resourceGroup\", \"updated_date\": \"updatedDate\"}]";
    String listNamespaceDetailsPath = "/api/v1/namespaces/details";
    server.enqueue(new MockResponse()
      .setHeader("Content-type", "application/json")
      .setResponseCode(200)
      .setBody(mockResponseBody));

    // Construct an instance of the ListNamespaceDetailsOptions model
    ListNamespaceDetailsOptions listNamespaceDetailsOptionsModel = new ListNamespaceDetailsOptions();

    // Invoke listNamespaceDetails() with a valid options model and verify the result
    Response<List<NamespaceDetails>> response = containerRegistryService.listNamespaceDetails(listNamespaceDetailsOptionsModel).execute();
    assertNotNull(response);
    List<NamespaceDetails> responseObj = response.getResult();
    assertNotNull(responseObj);

    // Verify the contents of the request sent to the mock server
    RecordedRequest request = server.takeRequest();
    assertNotNull(request);
    assertEquals(request.getMethod(), "GET");
    // Verify request path
    String parsedPath = TestUtilities.parseReqPath(request);
    assertEquals(parsedPath, listNamespaceDetailsPath);
    // Verify header parameters
    assertEquals(request.getHeader("Account"), "testString");
    // Verify that there is no query string
    Map<String, String> query = TestUtilities.parseQueryString(request);
    assertNull(query);
  }

  // Test the listNamespaceDetails operation with and without retries enabled
  @Test
  public void testListNamespaceDetailsWRetries() throws Throwable {
    containerRegistryService.enableRetries(4, 30);
    testListNamespaceDetailsWOptions();

    containerRegistryService.disableRetries();
    testListNamespaceDetailsWOptions();
  }

  // Test the createNamespace operation with a valid options model parameter
  @Test
  public void testCreateNamespaceWOptions() throws Throwable {
    // Register a mock response
    String mockResponseBody = "{\"namespace\": \"namespace\"}";
    String createNamespacePath = "/api/v1/namespaces/testString";
    server.enqueue(new MockResponse()
      .setHeader("Content-type", "application/json")
      .setResponseCode(200)
      .setBody(mockResponseBody));

    // Construct an instance of the CreateNamespaceOptions model
    CreateNamespaceOptions createNamespaceOptionsModel = new CreateNamespaceOptions.Builder()
      .name("testString")
      .xAuthResourceGroup("testString")
      .build();

    // Invoke createNamespace() with a valid options model and verify the result
    Response<Namespace> response = containerRegistryService.createNamespace(createNamespaceOptionsModel).execute();
    assertNotNull(response);
    Namespace responseObj = response.getResult();
    assertNotNull(responseObj);

    // Verify the contents of the request sent to the mock server
    RecordedRequest request = server.takeRequest();
    assertNotNull(request);
    assertEquals(request.getMethod(), "PUT");
    // Verify request path
    String parsedPath = TestUtilities.parseReqPath(request);
    assertEquals(parsedPath, createNamespacePath);
    // Verify header parameters
    assertEquals(request.getHeader("Account"), "testString");
    // Verify that there is no query string
    Map<String, String> query = TestUtilities.parseQueryString(request);
    assertNull(query);
  }

  // Test the createNamespace operation with and without retries enabled
  @Test
  public void testCreateNamespaceWRetries() throws Throwable {
    containerRegistryService.enableRetries(4, 30);
    testCreateNamespaceWOptions();

    containerRegistryService.disableRetries();
    testCreateNamespaceWOptions();
  }

  // Test the createNamespace operation with a null options model (negative test)
  @Test(expectedExceptions = IllegalArgumentException.class)
  public void testCreateNamespaceNoOptions() throws Throwable {
    server.enqueue(new MockResponse());
    containerRegistryService.createNamespace(null).execute();
  }

  // Test the assignNamespace operation with a valid options model parameter
  @Test
  public void testAssignNamespaceWOptions() throws Throwable {
    // Register a mock response
    String mockResponseBody = "{\"namespace\": \"namespace\"}";
    String assignNamespacePath = "/api/v1/namespaces/testString";
    server.enqueue(new MockResponse()
      .setHeader("Content-type", "application/json")
      .setResponseCode(200)
      .setBody(mockResponseBody));

    // Construct an instance of the AssignNamespaceOptions model
    AssignNamespaceOptions assignNamespaceOptionsModel = new AssignNamespaceOptions.Builder()
      .xAuthResourceGroup("testString")
      .name("testString")
      .build();

    // Invoke assignNamespace() with a valid options model and verify the result
    Response<Namespace> response = containerRegistryService.assignNamespace(assignNamespaceOptionsModel).execute();
    assertNotNull(response);
    Namespace responseObj = response.getResult();
    assertNotNull(responseObj);

    // Verify the contents of the request sent to the mock server
    RecordedRequest request = server.takeRequest();
    assertNotNull(request);
    assertEquals(request.getMethod(), "PATCH");
    // Verify request path
    String parsedPath = TestUtilities.parseReqPath(request);
    assertEquals(parsedPath, assignNamespacePath);
    // Verify header parameters
    assertEquals(request.getHeader("Account"), "testString");
    assertEquals(request.getHeader("X-Auth-Resource-Group"), "testString");
    // Verify that there is no query string
    Map<String, String> query = TestUtilities.parseQueryString(request);
    assertNull(query);
  }

  // Test the assignNamespace operation with and without retries enabled
  @Test
  public void testAssignNamespaceWRetries() throws Throwable {
    containerRegistryService.enableRetries(4, 30);
    testAssignNamespaceWOptions();

    containerRegistryService.disableRetries();
    testAssignNamespaceWOptions();
  }

  // Test the assignNamespace operation with a null options model (negative test)
  @Test(expectedExceptions = IllegalArgumentException.class)
  public void testAssignNamespaceNoOptions() throws Throwable {
    server.enqueue(new MockResponse());
    containerRegistryService.assignNamespace(null).execute();
  }

  // Test the deleteNamespace operation with a valid options model parameter
  @Test
  public void testDeleteNamespaceWOptions() throws Throwable {
    // Register a mock response
    String mockResponseBody = "";
    String deleteNamespacePath = "/api/v1/namespaces/testString";
    server.enqueue(new MockResponse()
      .setResponseCode(204)
      .setBody(mockResponseBody));

    // Construct an instance of the DeleteNamespaceOptions model
    DeleteNamespaceOptions deleteNamespaceOptionsModel = new DeleteNamespaceOptions.Builder()
      .name("testString")
      .build();

    // Invoke deleteNamespace() with a valid options model and verify the result
    Response<Void> response = containerRegistryService.deleteNamespace(deleteNamespaceOptionsModel).execute();
    assertNotNull(response);
    Void responseObj = response.getResult();
    assertNull(responseObj);

    // Verify the contents of the request sent to the mock server
    RecordedRequest request = server.takeRequest();
    assertNotNull(request);
    assertEquals(request.getMethod(), "DELETE");
    // Verify request path
    String parsedPath = TestUtilities.parseReqPath(request);
    assertEquals(parsedPath, deleteNamespacePath);
    // Verify header parameters
    assertEquals(request.getHeader("Account"), "testString");
    // Verify that there is no query string
    Map<String, String> query = TestUtilities.parseQueryString(request);
    assertNull(query);
  }

  // Test the deleteNamespace operation with and without retries enabled
  @Test
  public void testDeleteNamespaceWRetries() throws Throwable {
    containerRegistryService.enableRetries(4, 30);
    testDeleteNamespaceWOptions();

    containerRegistryService.disableRetries();
    testDeleteNamespaceWOptions();
  }

  // Test the deleteNamespace operation with a null options model (negative test)
  @Test(expectedExceptions = IllegalArgumentException.class)
  public void testDeleteNamespaceNoOptions() throws Throwable {
    server.enqueue(new MockResponse());
    containerRegistryService.deleteNamespace(null).execute();
  }

  // Test the getPlans operation with a valid options model parameter
  @Test
  public void testGetPlansWOptions() throws Throwable {
    // Register a mock response
    String mockResponseBody = "{\"plan\": \"plan\"}";
    String getPlansPath = "/api/v1/plans";
    server.enqueue(new MockResponse()
      .setHeader("Content-type", "application/json")
      .setResponseCode(200)
      .setBody(mockResponseBody));

    // Construct an instance of the GetPlansOptions model
    GetPlansOptions getPlansOptionsModel = new GetPlansOptions();

    // Invoke getPlans() with a valid options model and verify the result
    Response<Plan> response = containerRegistryService.getPlans(getPlansOptionsModel).execute();
    assertNotNull(response);
    Plan responseObj = response.getResult();
    assertNotNull(responseObj);

    // Verify the contents of the request sent to the mock server
    RecordedRequest request = server.takeRequest();
    assertNotNull(request);
    assertEquals(request.getMethod(), "GET");
    // Verify request path
    String parsedPath = TestUtilities.parseReqPath(request);
    assertEquals(parsedPath, getPlansPath);
    // Verify header parameters
    assertEquals(request.getHeader("Account"), "testString");
    // Verify that there is no query string
    Map<String, String> query = TestUtilities.parseQueryString(request);
    assertNull(query);
  }

  // Test the getPlans operation with and without retries enabled
  @Test
  public void testGetPlansWRetries() throws Throwable {
    containerRegistryService.enableRetries(4, 30);
    testGetPlansWOptions();

    containerRegistryService.disableRetries();
    testGetPlansWOptions();
  }

  // Test the updatePlans operation with a valid options model parameter
  @Test
  public void testUpdatePlansWOptions() throws Throwable {
    // Register a mock response
    String mockResponseBody = "";
    String updatePlansPath = "/api/v1/plans";
    server.enqueue(new MockResponse()
      .setResponseCode(200)
      .setBody(mockResponseBody));

    // Construct an instance of the UpdatePlansOptions model
    UpdatePlansOptions updatePlansOptionsModel = new UpdatePlansOptions.Builder()
      .plan("Standard")
      .build();

    // Invoke updatePlans() with a valid options model and verify the result
    Response<Void> response = containerRegistryService.updatePlans(updatePlansOptionsModel).execute();
    assertNotNull(response);
    Void responseObj = response.getResult();
    assertNull(responseObj);

    // Verify the contents of the request sent to the mock server
    RecordedRequest request = server.takeRequest();
    assertNotNull(request);
    assertEquals(request.getMethod(), "PATCH");
    // Verify request path
    String parsedPath = TestUtilities.parseReqPath(request);
    assertEquals(parsedPath, updatePlansPath);
    // Verify header parameters
    assertEquals(request.getHeader("Account"), "testString");
    // Verify that there is no query string
    Map<String, String> query = TestUtilities.parseQueryString(request);
    assertNull(query);
  }

  // Test the updatePlans operation with and without retries enabled
  @Test
  public void testUpdatePlansWRetries() throws Throwable {
    containerRegistryService.enableRetries(4, 30);
    testUpdatePlansWOptions();

    containerRegistryService.disableRetries();
    testUpdatePlansWOptions();
  }

  // Test the getQuota operation with a valid options model parameter
  @Test
  public void testGetQuotaWOptions() throws Throwable {
    // Register a mock response
    String mockResponseBody = "{\"limit\": {\"storage_bytes\": 12, \"traffic_bytes\": 12}, \"usage\": {\"storage_bytes\": 12, \"traffic_bytes\": 12}}";
    String getQuotaPath = "/api/v1/quotas";
    server.enqueue(new MockResponse()
      .setHeader("Content-type", "application/json")
      .setResponseCode(200)
      .setBody(mockResponseBody));

    // Construct an instance of the GetQuotaOptions model
    GetQuotaOptions getQuotaOptionsModel = new GetQuotaOptions();

    // Invoke getQuota() with a valid options model and verify the result
    Response<Quota> response = containerRegistryService.getQuota(getQuotaOptionsModel).execute();
    assertNotNull(response);
    Quota responseObj = response.getResult();
    assertNotNull(responseObj);

    // Verify the contents of the request sent to the mock server
    RecordedRequest request = server.takeRequest();
    assertNotNull(request);
    assertEquals(request.getMethod(), "GET");
    // Verify request path
    String parsedPath = TestUtilities.parseReqPath(request);
    assertEquals(parsedPath, getQuotaPath);
    // Verify header parameters
    assertEquals(request.getHeader("Account"), "testString");
    // Verify that there is no query string
    Map<String, String> query = TestUtilities.parseQueryString(request);
    assertNull(query);
  }

  // Test the getQuota operation with and without retries enabled
  @Test
  public void testGetQuotaWRetries() throws Throwable {
    containerRegistryService.enableRetries(4, 30);
    testGetQuotaWOptions();

    containerRegistryService.disableRetries();
    testGetQuotaWOptions();
  }

  // Test the updateQuota operation with a valid options model parameter
  @Test
  public void testUpdateQuotaWOptions() throws Throwable {
    // Register a mock response
    String mockResponseBody = "";
    String updateQuotaPath = "/api/v1/quotas";
    server.enqueue(new MockResponse()
      .setResponseCode(200)
      .setBody(mockResponseBody));

    // Construct an instance of the UpdateQuotaOptions model
    UpdateQuotaOptions updateQuotaOptionsModel = new UpdateQuotaOptions.Builder()
      .storageMegabytes(Long.valueOf("26"))
      .trafficMegabytes(Long.valueOf("480"))
      .build();

    // Invoke updateQuota() with a valid options model and verify the result
    Response<Void> response = containerRegistryService.updateQuota(updateQuotaOptionsModel).execute();
    assertNotNull(response);
    Void responseObj = response.getResult();
    assertNull(responseObj);

    // Verify the contents of the request sent to the mock server
    RecordedRequest request = server.takeRequest();
    assertNotNull(request);
    assertEquals(request.getMethod(), "PATCH");
    // Verify request path
    String parsedPath = TestUtilities.parseReqPath(request);
    assertEquals(parsedPath, updateQuotaPath);
    // Verify header parameters
    assertEquals(request.getHeader("Account"), "testString");
    // Verify that there is no query string
    Map<String, String> query = TestUtilities.parseQueryString(request);
    assertNull(query);
  }

  // Test the updateQuota operation with and without retries enabled
  @Test
  public void testUpdateQuotaWRetries() throws Throwable {
    containerRegistryService.enableRetries(4, 30);
    testUpdateQuotaWOptions();

    containerRegistryService.disableRetries();
    testUpdateQuotaWOptions();
  }

  // Test the listRetentionPolicies operation with a valid options model parameter
  @Test
  public void testListRetentionPoliciesWOptions() throws Throwable {
    // Register a mock response
    String mockResponseBody = "{\"mapKey\": {\"images_per_repo\": 13, \"namespace\": \"namespace\", \"retain_untagged\": true}}";
    String listRetentionPoliciesPath = "/api/v1/retentions";
    server.enqueue(new MockResponse()
      .setHeader("Content-type", "application/json")
      .setResponseCode(200)
      .setBody(mockResponseBody));

    // Construct an instance of the ListRetentionPoliciesOptions model
    ListRetentionPoliciesOptions listRetentionPoliciesOptionsModel = new ListRetentionPoliciesOptions();

    // Invoke listRetentionPolicies() with a valid options model and verify the result
    Response<Map<String, RetentionPolicy>> response = containerRegistryService.listRetentionPolicies(listRetentionPoliciesOptionsModel).execute();
    assertNotNull(response);
    Map<String, RetentionPolicy> responseObj = response.getResult();
    assertNotNull(responseObj);

    // Verify the contents of the request sent to the mock server
    RecordedRequest request = server.takeRequest();
    assertNotNull(request);
    assertEquals(request.getMethod(), "GET");
    // Verify request path
    String parsedPath = TestUtilities.parseReqPath(request);
    assertEquals(parsedPath, listRetentionPoliciesPath);
    // Verify header parameters
    assertEquals(request.getHeader("Account"), "testString");
    // Verify that there is no query string
    Map<String, String> query = TestUtilities.parseQueryString(request);
    assertNull(query);
  }

  // Test the listRetentionPolicies operation with and without retries enabled
  @Test
  public void testListRetentionPoliciesWRetries() throws Throwable {
    containerRegistryService.enableRetries(4, 30);
    testListRetentionPoliciesWOptions();

    containerRegistryService.disableRetries();
    testListRetentionPoliciesWOptions();
  }

  // Test the setRetentionPolicy operation with a valid options model parameter
  @Test
  public void testSetRetentionPolicyWOptions() throws Throwable {
    // Register a mock response
    String mockResponseBody = "";
    String setRetentionPolicyPath = "/api/v1/retentions";
    server.enqueue(new MockResponse()
      .setResponseCode(200)
      .setBody(mockResponseBody));

    // Construct an instance of the SetRetentionPolicyOptions model
    SetRetentionPolicyOptions setRetentionPolicyOptionsModel = new SetRetentionPolicyOptions.Builder()
      .namespace("birds")
      .imagesPerRepo(Long.valueOf("10"))
      .retainUntagged(false)
      .build();

    // Invoke setRetentionPolicy() with a valid options model and verify the result
    Response<Void> response = containerRegistryService.setRetentionPolicy(setRetentionPolicyOptionsModel).execute();
    assertNotNull(response);
    Void responseObj = response.getResult();
    assertNull(responseObj);

    // Verify the contents of the request sent to the mock server
    RecordedRequest request = server.takeRequest();
    assertNotNull(request);
    assertEquals(request.getMethod(), "POST");
    // Verify request path
    String parsedPath = TestUtilities.parseReqPath(request);
    assertEquals(parsedPath, setRetentionPolicyPath);
    // Verify header parameters
    assertEquals(request.getHeader("Account"), "testString");
    // Verify that there is no query string
    Map<String, String> query = TestUtilities.parseQueryString(request);
    assertNull(query);
  }

  // Test the setRetentionPolicy operation with and without retries enabled
  @Test
  public void testSetRetentionPolicyWRetries() throws Throwable {
    containerRegistryService.enableRetries(4, 30);
    testSetRetentionPolicyWOptions();

    containerRegistryService.disableRetries();
    testSetRetentionPolicyWOptions();
  }

  // Test the setRetentionPolicy operation with a null options model (negative test)
  @Test(expectedExceptions = IllegalArgumentException.class)
  public void testSetRetentionPolicyNoOptions() throws Throwable {
    server.enqueue(new MockResponse());
    containerRegistryService.setRetentionPolicy(null).execute();
  }

  // Test the analyzeRetentionPolicy operation with a valid options model parameter
  @Test
  public void testAnalyzeRetentionPolicyWOptions() throws Throwable {
    // Register a mock response
    String mockResponseBody = "{\"mapKey\": [\"inner\"]}";
    String analyzeRetentionPolicyPath = "/api/v1/retentions/analyze";
    server.enqueue(new MockResponse()
      .setHeader("Content-type", "application/json")
      .setResponseCode(200)
      .setBody(mockResponseBody));

    // Construct an instance of the AnalyzeRetentionPolicyOptions model
    AnalyzeRetentionPolicyOptions analyzeRetentionPolicyOptionsModel = new AnalyzeRetentionPolicyOptions.Builder()
      .namespace("birds")
      .imagesPerRepo(Long.valueOf("10"))
      .retainUntagged(false)
      .build();

    // Invoke analyzeRetentionPolicy() with a valid options model and verify the result
    Response<Map<String, List<String>>> response = containerRegistryService.analyzeRetentionPolicy(analyzeRetentionPolicyOptionsModel).execute();
    assertNotNull(response);
    Map<String, List<String>> responseObj = response.getResult();
    assertNotNull(responseObj);

    // Verify the contents of the request sent to the mock server
    RecordedRequest request = server.takeRequest();
    assertNotNull(request);
    assertEquals(request.getMethod(), "POST");
    // Verify request path
    String parsedPath = TestUtilities.parseReqPath(request);
    assertEquals(parsedPath, analyzeRetentionPolicyPath);
    // Verify header parameters
    assertEquals(request.getHeader("Account"), "testString");
    // Verify that there is no query string
    Map<String, String> query = TestUtilities.parseQueryString(request);
    assertNull(query);
  }

  // Test the analyzeRetentionPolicy operation with and without retries enabled
  @Test
  public void testAnalyzeRetentionPolicyWRetries() throws Throwable {
    containerRegistryService.enableRetries(4, 30);
    testAnalyzeRetentionPolicyWOptions();

    containerRegistryService.disableRetries();
    testAnalyzeRetentionPolicyWOptions();
  }

  // Test the analyzeRetentionPolicy operation with a null options model (negative test)
  @Test(expectedExceptions = IllegalArgumentException.class)
  public void testAnalyzeRetentionPolicyNoOptions() throws Throwable {
    server.enqueue(new MockResponse());
    containerRegistryService.analyzeRetentionPolicy(null).execute();
  }

  // Test the getRetentionPolicy operation with a valid options model parameter
  @Test
  public void testGetRetentionPolicyWOptions() throws Throwable {
    // Register a mock response
    String mockResponseBody = "{\"images_per_repo\": 13, \"namespace\": \"namespace\", \"retain_untagged\": true}";
    String getRetentionPolicyPath = "/api/v1/retentions/testString";
    server.enqueue(new MockResponse()
      .setHeader("Content-type", "application/json")
      .setResponseCode(200)
      .setBody(mockResponseBody));

    // Construct an instance of the GetRetentionPolicyOptions model
    GetRetentionPolicyOptions getRetentionPolicyOptionsModel = new GetRetentionPolicyOptions.Builder()
      .namespace("testString")
      .build();

    // Invoke getRetentionPolicy() with a valid options model and verify the result
    Response<RetentionPolicy> response = containerRegistryService.getRetentionPolicy(getRetentionPolicyOptionsModel).execute();
    assertNotNull(response);
    RetentionPolicy responseObj = response.getResult();
    assertNotNull(responseObj);

    // Verify the contents of the request sent to the mock server
    RecordedRequest request = server.takeRequest();
    assertNotNull(request);
    assertEquals(request.getMethod(), "GET");
    // Verify request path
    String parsedPath = TestUtilities.parseReqPath(request);
    assertEquals(parsedPath, getRetentionPolicyPath);
    // Verify header parameters
    assertEquals(request.getHeader("Account"), "testString");
    // Verify that there is no query string
    Map<String, String> query = TestUtilities.parseQueryString(request);
    assertNull(query);
  }

  // Test the getRetentionPolicy operation with and without retries enabled
  @Test
  public void testGetRetentionPolicyWRetries() throws Throwable {
    containerRegistryService.enableRetries(4, 30);
    testGetRetentionPolicyWOptions();

    containerRegistryService.disableRetries();
    testGetRetentionPolicyWOptions();
  }

  // Test the getRetentionPolicy operation with a null options model (negative test)
  @Test(expectedExceptions = IllegalArgumentException.class)
  public void testGetRetentionPolicyNoOptions() throws Throwable {
    server.enqueue(new MockResponse());
    containerRegistryService.getRetentionPolicy(null).execute();
  }

  // Test the getSettings operation with a valid options model parameter
  @Test
  public void testGetSettingsWOptions() throws Throwable {
    // Register a mock response
    String mockResponseBody = "{\"platform_metrics\": false}";
    String getSettingsPath = "/api/v1/settings";
    server.enqueue(new MockResponse()
      .setHeader("Content-type", "application/json")
      .setResponseCode(200)
      .setBody(mockResponseBody));

    // Construct an instance of the GetSettingsOptions model
    GetSettingsOptions getSettingsOptionsModel = new GetSettingsOptions();

    // Invoke getSettings() with a valid options model and verify the result
    Response<AccountSettings> response = containerRegistryService.getSettings(getSettingsOptionsModel).execute();
    assertNotNull(response);
    AccountSettings responseObj = response.getResult();
    assertNotNull(responseObj);

    // Verify the contents of the request sent to the mock server
    RecordedRequest request = server.takeRequest();
    assertNotNull(request);
    assertEquals(request.getMethod(), "GET");
    // Verify request path
    String parsedPath = TestUtilities.parseReqPath(request);
    assertEquals(parsedPath, getSettingsPath);
    // Verify header parameters
    assertEquals(request.getHeader("Account"), "testString");
    // Verify that there is no query string
    Map<String, String> query = TestUtilities.parseQueryString(request);
    assertNull(query);
  }

  // Test the getSettings operation with and without retries enabled
  @Test
  public void testGetSettingsWRetries() throws Throwable {
    containerRegistryService.enableRetries(4, 30);
    testGetSettingsWOptions();

    containerRegistryService.disableRetries();
    testGetSettingsWOptions();
  }

  // Test the updateSettings operation with a valid options model parameter
  @Test
  public void testUpdateSettingsWOptions() throws Throwable {
    // Register a mock response
    String mockResponseBody = "";
    String updateSettingsPath = "/api/v1/settings";
    server.enqueue(new MockResponse()
      .setResponseCode(204)
      .setBody(mockResponseBody));

    // Construct an instance of the UpdateSettingsOptions model
    UpdateSettingsOptions updateSettingsOptionsModel = new UpdateSettingsOptions.Builder()
      .platformMetrics(true)
      .build();

    // Invoke updateSettings() with a valid options model and verify the result
    Response<Void> response = containerRegistryService.updateSettings(updateSettingsOptionsModel).execute();
    assertNotNull(response);
    Void responseObj = response.getResult();
    assertNull(responseObj);

    // Verify the contents of the request sent to the mock server
    RecordedRequest request = server.takeRequest();
    assertNotNull(request);
    assertEquals(request.getMethod(), "PATCH");
    // Verify request path
    String parsedPath = TestUtilities.parseReqPath(request);
    assertEquals(parsedPath, updateSettingsPath);
    // Verify header parameters
    assertEquals(request.getHeader("Account"), "testString");
    // Verify that there is no query string
    Map<String, String> query = TestUtilities.parseQueryString(request);
    assertNull(query);
  }

  // Test the updateSettings operation with and without retries enabled
  @Test
  public void testUpdateSettingsWRetries() throws Throwable {
    containerRegistryService.enableRetries(4, 30);
    testUpdateSettingsWOptions();

    containerRegistryService.disableRetries();
    testUpdateSettingsWOptions();
  }

  // Test the deleteImageTag operation with a valid options model parameter
  @Test
  public void testDeleteImageTagWOptions() throws Throwable {
    // Register a mock response
    String mockResponseBody = "{\"Untagged\": \"untagged\"}";
    String deleteImageTagPath = "/api/v1/tags/testString";
    server.enqueue(new MockResponse()
      .setHeader("Content-type", "application/json")
      .setResponseCode(200)
      .setBody(mockResponseBody));

    // Construct an instance of the DeleteImageTagOptions model
    DeleteImageTagOptions deleteImageTagOptionsModel = new DeleteImageTagOptions.Builder()
      .image("testString")
      .build();

    // Invoke deleteImageTag() with a valid options model and verify the result
    Response<ImageDeleteResult> response = containerRegistryService.deleteImageTag(deleteImageTagOptionsModel).execute();
    assertNotNull(response);
    ImageDeleteResult responseObj = response.getResult();
    assertNotNull(responseObj);

    // Verify the contents of the request sent to the mock server
    RecordedRequest request = server.takeRequest();
    assertNotNull(request);
    assertEquals(request.getMethod(), "DELETE");
    // Verify request path
    String parsedPath = TestUtilities.parseReqPath(request);
    assertEquals(parsedPath, deleteImageTagPath);
    // Verify header parameters
    assertEquals(request.getHeader("Account"), "testString");
    // Verify that there is no query string
    Map<String, String> query = TestUtilities.parseQueryString(request);
    assertNull(query);
  }

  // Test the deleteImageTag operation with and without retries enabled
  @Test
  public void testDeleteImageTagWRetries() throws Throwable {
    containerRegistryService.enableRetries(4, 30);
    testDeleteImageTagWOptions();

    containerRegistryService.disableRetries();
    testDeleteImageTagWOptions();
  }

  // Test the deleteImageTag operation with a null options model (negative test)
  @Test(expectedExceptions = IllegalArgumentException.class)
  public void testDeleteImageTagNoOptions() throws Throwable {
    server.enqueue(new MockResponse());
    containerRegistryService.deleteImageTag(null).execute();
  }

  // Test the listDeletedImages operation with a valid options model parameter
  @Test
  public void testListDeletedImagesWOptions() throws Throwable {
    // Register a mock response
    String mockResponseBody = "{\"mapKey\": {\"daysUntilExpiry\": 15, \"tags\": [\"tags\"]}}";
    String listDeletedImagesPath = "/api/v1/trash";
    server.enqueue(new MockResponse()
      .setHeader("Content-type", "application/json")
      .setResponseCode(200)
      .setBody(mockResponseBody));

    // Construct an instance of the ListDeletedImagesOptions model
    ListDeletedImagesOptions listDeletedImagesOptionsModel = new ListDeletedImagesOptions.Builder()
      .namespace("testString")
      .build();

    // Invoke listDeletedImages() with a valid options model and verify the result
    Response<Map<String, Trash>> response = containerRegistryService.listDeletedImages(listDeletedImagesOptionsModel).execute();
    assertNotNull(response);
    Map<String, Trash> responseObj = response.getResult();
    assertNotNull(responseObj);

    // Verify the contents of the request sent to the mock server
    RecordedRequest request = server.takeRequest();
    assertNotNull(request);
    assertEquals(request.getMethod(), "GET");
    // Verify request path
    String parsedPath = TestUtilities.parseReqPath(request);
    assertEquals(parsedPath, listDeletedImagesPath);
    // Verify header parameters
    assertEquals(request.getHeader("Account"), "testString");
    // Verify query params
    Map<String, String> query = TestUtilities.parseQueryString(request);
    assertNotNull(query);
    assertEquals(query.get("namespace"), "testString");
  }

  // Test the listDeletedImages operation with and without retries enabled
  @Test
  public void testListDeletedImagesWRetries() throws Throwable {
    containerRegistryService.enableRetries(4, 30);
    testListDeletedImagesWOptions();

    containerRegistryService.disableRetries();
    testListDeletedImagesWOptions();
  }

  // Test the restoreTags operation with a valid options model parameter
  @Test
  public void testRestoreTagsWOptions() throws Throwable {
    // Register a mock response
    String mockResponseBody = "{\"successful\": [\"successful\"], \"unsuccessful\": [\"unsuccessful\"]}";
    String restoreTagsPath = "/api/v1/trash/testString/restoretags";
    server.enqueue(new MockResponse()
      .setHeader("Content-type", "application/json")
      .setResponseCode(200)
      .setBody(mockResponseBody));

    // Construct an instance of the RestoreTagsOptions model
    RestoreTagsOptions restoreTagsOptionsModel = new RestoreTagsOptions.Builder()
      .digest("testString")
      .build();

    // Invoke restoreTags() with a valid options model and verify the result
    Response<RestoreResult> response = containerRegistryService.restoreTags(restoreTagsOptionsModel).execute();
    assertNotNull(response);
    RestoreResult responseObj = response.getResult();
    assertNotNull(responseObj);

    // Verify the contents of the request sent to the mock server
    RecordedRequest request = server.takeRequest();
    assertNotNull(request);
    assertEquals(request.getMethod(), "POST");
    // Verify request path
    String parsedPath = TestUtilities.parseReqPath(request);
    assertEquals(parsedPath, restoreTagsPath);
    // Verify header parameters
    assertEquals(request.getHeader("Account"), "testString");
    // Verify that there is no query string
    Map<String, String> query = TestUtilities.parseQueryString(request);
    assertNull(query);
  }

  // Test the restoreTags operation with and without retries enabled
  @Test
  public void testRestoreTagsWRetries() throws Throwable {
    containerRegistryService.enableRetries(4, 30);
    testRestoreTagsWOptions();

    containerRegistryService.disableRetries();
    testRestoreTagsWOptions();
  }

  // Test the restoreTags operation with a null options model (negative test)
  @Test(expectedExceptions = IllegalArgumentException.class)
  public void testRestoreTagsNoOptions() throws Throwable {
    server.enqueue(new MockResponse());
    containerRegistryService.restoreTags(null).execute();
  }

  // Test the restoreImage operation with a valid options model parameter
  @Test
  public void testRestoreImageWOptions() throws Throwable {
    // Register a mock response
    String mockResponseBody = "";
    String restoreImagePath = "/api/v1/trash/testString/restore";
    server.enqueue(new MockResponse()
      .setResponseCode(200)
      .setBody(mockResponseBody));

    // Construct an instance of the RestoreImageOptions model
    RestoreImageOptions restoreImageOptionsModel = new RestoreImageOptions.Builder()
      .image("testString")
      .build();

    // Invoke restoreImage() with a valid options model and verify the result
    Response<Void> response = containerRegistryService.restoreImage(restoreImageOptionsModel).execute();
    assertNotNull(response);
    Void responseObj = response.getResult();
    assertNull(responseObj);

    // Verify the contents of the request sent to the mock server
    RecordedRequest request = server.takeRequest();
    assertNotNull(request);
    assertEquals(request.getMethod(), "POST");
    // Verify request path
    String parsedPath = TestUtilities.parseReqPath(request);
    assertEquals(parsedPath, restoreImagePath);
    // Verify header parameters
    assertEquals(request.getHeader("Account"), "testString");
    // Verify that there is no query string
    Map<String, String> query = TestUtilities.parseQueryString(request);
    assertNull(query);
  }

  // Test the restoreImage operation with and without retries enabled
  @Test
  public void testRestoreImageWRetries() throws Throwable {
    containerRegistryService.enableRetries(4, 30);
    testRestoreImageWOptions();

    containerRegistryService.disableRetries();
    testRestoreImageWOptions();
  }

  // Test the restoreImage operation with a null options model (negative test)
  @Test(expectedExceptions = IllegalArgumentException.class)
  public void testRestoreImageNoOptions() throws Throwable {
    server.enqueue(new MockResponse());
    containerRegistryService.restoreImage(null).execute();
  }

  // Perform setup needed before each test method
  @BeforeMethod
  public void beforeEachTest() {
    // Start the mock server.
    try {
      server = new MockWebServer();
      server.start();
    } catch (IOException err) {
      fail("Failed to instantiate mock web server");
    }

    // Construct an instance of the service
    constructClientService();
  }

  // Perform tear down after each test method
  @AfterMethod
  public void afterEachTest() throws IOException {
    server.shutdown();
    containerRegistryService = null;
  }

  // Constructs an instance of the service to be used by the tests
  public void constructClientService() {
    System.setProperty("TESTSERVICE_AUTH_TYPE", "noAuth");
    final String serviceName = "testService";
    // set mock values for global params
    String account = "testString";

    containerRegistryService = ContainerRegistry.newInstance(account, serviceName);
    String url = server.url("/").toString();
    containerRegistryService.setServiceUrl(url);
  }
}