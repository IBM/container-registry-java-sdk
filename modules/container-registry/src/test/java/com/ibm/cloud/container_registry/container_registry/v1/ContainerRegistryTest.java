/*
 * (C) Copyright IBM Corp. 2021.
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
import com.ibm.cloud.sdk.core.util.EnvironmentUtils;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.testng.PowerMockTestCase;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static org.testng.Assert.*;

/**
 * Unit test class for the ContainerRegistry service.
 */
@PrepareForTest({ EnvironmentUtils.class })
@PowerMockIgnore({"javax.net.ssl.*", "org.mockito.*"})
public class ContainerRegistryTest extends PowerMockTestCase {

  final HashMap<String, InputStream> mockStreamMap = TestUtilities.createMockStreamMap();
  final List<FileWithMetadata> mockListFileWithMetadata = TestUtilities.creatMockListFileWithMetadata();

  protected MockWebServer server;
  protected ContainerRegistry containerRegistryService;

  // Creates a mock set of environment variables that are returned by EnvironmentUtils.getenv().
  private Map<String, String> getTestProcessEnvironment() {
    Map<String, String> env = new HashMap<>();
    env.put("TESTSERVICE_AUTH_TYPE", "noAuth");
    return env;
  }

  public void constructClientService() throws Throwable {
    PowerMockito.spy(EnvironmentUtils.class);
    PowerMockito.when(EnvironmentUtils.getenv()).thenReturn(getTestProcessEnvironment());
    final String serviceName = "testService";
    // set mock values for global params
    String account = "testString";

    containerRegistryService = ContainerRegistry.newInstance(account, serviceName);
    String url = server.url("/").toString();
    containerRegistryService.setServiceUrl(url);
  }

  /**
  * Negative Test - construct the service with a null authenticator.
  */
  @Test(expectedExceptions = IllegalArgumentException.class)
  public void testConstructorWithNullAuthenticator() throws Throwable {
    final String serviceName = "testService";
    // set mock values for global params
    String account = "testString";

    new ContainerRegistry(account, serviceName, null);
  }


  @Test
  public void testGetAccount() throws Throwable {
    constructClientService();
    assertEquals(containerRegistryService.getAccount(), "testString");
  }

  @Test
  public void testGetAuthWOptions() throws Throwable {
    // Schedule some responses.
    String mockResponseBody = "{\"iam_authz\": true, \"private_only\": false}";
    String getAuthPath = "/api/v1/auth";

    server.enqueue(new MockResponse()
    .setHeader("Content-type", "application/json")
    .setResponseCode(200)
    .setBody(mockResponseBody));

    constructClientService();

    // Construct an instance of the GetAuthOptions model
    GetAuthOptions getAuthOptionsModel = new GetAuthOptions();

    // Invoke operation with valid options model (positive test)
    Response<AuthOptions> response = containerRegistryService.getAuth(getAuthOptionsModel).execute();
    assertNotNull(response);
    AuthOptions responseObj = response.getResult();
    assertNotNull(responseObj);

    // Verify the contents of the request
    RecordedRequest request = server.takeRequest();
    assertNotNull(request);
    assertEquals(request.getMethod(), "GET");
    assertEquals(request.getHeader("Account"), "testString");

    // Check query
    Map<String, String> query = TestUtilities.parseQueryString(request);
    assertNull(query);

    // Check request path
    String parsedPath = TestUtilities.parseReqPath(request);
    assertEquals(parsedPath, getAuthPath);
  }

  @Test
  public void testUpdateAuthWOptions() throws Throwable {
    // Schedule some responses.
    String mockResponseBody = "";
    String updateAuthPath = "/api/v1/auth";

    server.enqueue(new MockResponse()
    .setResponseCode(204)
    .setBody(mockResponseBody));

    constructClientService();

    // Construct an instance of the UpdateAuthOptions model
    UpdateAuthOptions updateAuthOptionsModel = new UpdateAuthOptions.Builder()
    .iamAuthz(true)
    .privateOnly(true)
    .build();

    // Invoke operation with valid options model (positive test)
    Response<Void> response = containerRegistryService.updateAuth(updateAuthOptionsModel).execute();
    assertNotNull(response);
    Void responseObj = response.getResult();
    // Response does not have a return type. Check that the result is null.
    assertNull(responseObj);

    // Verify the contents of the request
    RecordedRequest request = server.takeRequest();
    assertNotNull(request);
    assertEquals(request.getMethod(), "PATCH");
    assertEquals(request.getHeader("Account"), "testString");

    // Check query
    Map<String, String> query = TestUtilities.parseQueryString(request);
    assertNull(query);

    // Check request path
    String parsedPath = TestUtilities.parseReqPath(request);
    assertEquals(parsedPath, updateAuthPath);
  }

  @Test
  public void testListImagesWOptions() throws Throwable {
    // Schedule some responses.
    String mockResponseBody = "[{\"ConfigurationIssueCount\": 23, \"Created\": 7, \"DigestTags\": {\"mapKey\": [\"inner\"]}, \"ExemptIssueCount\": 16, \"Id\": \"id\", \"IssueCount\": 10, \"Labels\": {\"mapKey\": \"inner\"}, \"ManifestType\": \"manifestType\", \"ParentId\": \"parentId\", \"RepoDigests\": [\"repoDigests\"], \"RepoTags\": [\"repoTags\"], \"Size\": 4, \"VirtualSize\": 11, \"VulnerabilityCount\": 18, \"Vulnerable\": \"vulnerable\"}]";
    String listImagesPath = "/api/v1/images";

    server.enqueue(new MockResponse()
    .setHeader("Content-type", "application/json")
    .setResponseCode(200)
    .setBody(mockResponseBody));

    constructClientService();

    // Construct an instance of the ListImagesOptions model
    ListImagesOptions listImagesOptionsModel = new ListImagesOptions.Builder()
    .namespace("testString")
    .includeIbm(true)
    .includePrivate(true)
    .includeManifestLists(true)
    .vulnerabilities(true)
    .repository("testString")
    .build();

    // Invoke operation with valid options model (positive test)
    Response<List<RemoteAPIImage>> response = containerRegistryService.listImages(listImagesOptionsModel).execute();
    assertNotNull(response);
    List<RemoteAPIImage> responseObj = response.getResult();
    assertNotNull(responseObj);

    // Verify the contents of the request
    RecordedRequest request = server.takeRequest();
    assertNotNull(request);
    assertEquals(request.getMethod(), "GET");
    assertEquals(request.getHeader("Account"), "testString");

    // Check query
    Map<String, String> query = TestUtilities.parseQueryString(request);
    assertNotNull(query);
    // Get query params
    assertEquals(query.get("namespace"), "testString");
    assertEquals(Boolean.valueOf(query.get("includeIBM")), Boolean.valueOf(true));
    assertEquals(Boolean.valueOf(query.get("includePrivate")), Boolean.valueOf(true));
    assertEquals(Boolean.valueOf(query.get("includeManifestLists")), Boolean.valueOf(true));
    assertEquals(Boolean.valueOf(query.get("vulnerabilities")), Boolean.valueOf(true));
    assertEquals(query.get("repository"), "testString");
    // Check request path
    String parsedPath = TestUtilities.parseReqPath(request);
    assertEquals(parsedPath, listImagesPath);
  }

  @Test
  public void testBulkDeleteImagesWOptions() throws Throwable {
    // Schedule some responses.
    String mockResponseBody = "{\"error\": {\"mapKey\": {\"code\": \"code\", \"message\": \"message\"}}, \"success\": [\"success\"]}";
    String bulkDeleteImagesPath = "/api/v1/images/bulkdelete";

    server.enqueue(new MockResponse()
    .setHeader("Content-type", "application/json")
    .setResponseCode(200)
    .setBody(mockResponseBody));

    constructClientService();

    // Construct an instance of the BulkDeleteImagesOptions model
    BulkDeleteImagesOptions bulkDeleteImagesOptionsModel = new BulkDeleteImagesOptions.Builder()
    .bulkDelete(new java.util.ArrayList<String>(java.util.Arrays.asList("us.icr.io/birds/woodpecker@sha256:38f97dd92769b18ca82ad9ab6667af47306e66fea5b446937eea68b10ab4bbbb", "us.icr.io/birds/bird@sha256:38f97dd92769b18ca82ad9ab6667af47306e66fea5b446937eea68b10ab4dddd")))
    .build();

    // Invoke operation with valid options model (positive test)
    Response<ImageBulkDeleteResult> response = containerRegistryService.bulkDeleteImages(bulkDeleteImagesOptionsModel).execute();
    assertNotNull(response);
    ImageBulkDeleteResult responseObj = response.getResult();
    assertNotNull(responseObj);

    // Verify the contents of the request
    RecordedRequest request = server.takeRequest();
    assertNotNull(request);
    assertEquals(request.getMethod(), "POST");
    assertEquals(request.getHeader("Account"), "testString");

    // Check query
    Map<String, String> query = TestUtilities.parseQueryString(request);
    assertNull(query);

    // Check request path
    String parsedPath = TestUtilities.parseReqPath(request);
    assertEquals(parsedPath, bulkDeleteImagesPath);
  }

  // Test the bulkDeleteImages operation with null options model parameter
  @Test(expectedExceptions = IllegalArgumentException.class)
  public void testBulkDeleteImagesNoOptions() throws Throwable {
    // construct the service
    constructClientService();

    server.enqueue(new MockResponse());

    // Invoke operation with null options model (negative test)
    containerRegistryService.bulkDeleteImages(null).execute();
  }

  @Test
  public void testListImageDigestsWOptions() throws Throwable {
    // Schedule some responses.
    String mockResponseBody = "[{\"created\": 7, \"id\": \"id\", \"manifestType\": \"manifestType\", \"repoTags\": {\"mapKey\": {\"mapKey\": {\"configurationIssueCount\": 23, \"exemptIssueCount\": 16, \"issueCount\": 10, \"vulnerabilityCount\": 18, \"vulnerable\": \"vulnerable\"}}}, \"size\": 4}]";
    String listImageDigestsPath = "/api/v1/images/digests";

    server.enqueue(new MockResponse()
    .setHeader("Content-type", "application/json")
    .setResponseCode(200)
    .setBody(mockResponseBody));

    constructClientService();

    // Construct an instance of the ListImageDigestsOptions model
    ListImageDigestsOptions listImageDigestsOptionsModel = new ListImageDigestsOptions.Builder()
    .excludeTagged(false)
    .excludeVa(false)
    .includeIbm(false)
    .repositories(new java.util.ArrayList<String>(java.util.Arrays.asList("testString")))
    .build();

    // Invoke operation with valid options model (positive test)
    Response<List<ImageDigest>> response = containerRegistryService.listImageDigests(listImageDigestsOptionsModel).execute();
    assertNotNull(response);
    List<ImageDigest> responseObj = response.getResult();
    assertNotNull(responseObj);

    // Verify the contents of the request
    RecordedRequest request = server.takeRequest();
    assertNotNull(request);
    assertEquals(request.getMethod(), "POST");
    assertEquals(request.getHeader("Account"), "testString");

    // Check query
    Map<String, String> query = TestUtilities.parseQueryString(request);
    assertNull(query);

    // Check request path
    String parsedPath = TestUtilities.parseReqPath(request);
    assertEquals(parsedPath, listImageDigestsPath);
  }

  @Test
  public void testTagImageWOptions() throws Throwable {
    // Schedule some responses.
    String mockResponseBody = "";
    String tagImagePath = "/api/v1/images/tags";

    server.enqueue(new MockResponse()
    .setResponseCode(201)
    .setBody(mockResponseBody));

    constructClientService();

    // Construct an instance of the TagImageOptions model
    TagImageOptions tagImageOptionsModel = new TagImageOptions.Builder()
    .fromimage("testString")
    .toimage("testString")
    .build();

    // Invoke operation with valid options model (positive test)
    Response<Void> response = containerRegistryService.tagImage(tagImageOptionsModel).execute();
    assertNotNull(response);
    Void responseObj = response.getResult();
    // Response does not have a return type. Check that the result is null.
    assertNull(responseObj);

    // Verify the contents of the request
    RecordedRequest request = server.takeRequest();
    assertNotNull(request);
    assertEquals(request.getMethod(), "POST");
    assertEquals(request.getHeader("Account"), "testString");

    // Check query
    Map<String, String> query = TestUtilities.parseQueryString(request);
    assertNotNull(query);
    // Get query params
    assertEquals(query.get("fromimage"), "testString");
    assertEquals(query.get("toimage"), "testString");
    // Check request path
    String parsedPath = TestUtilities.parseReqPath(request);
    assertEquals(parsedPath, tagImagePath);
  }

  // Test the tagImage operation with null options model parameter
  @Test(expectedExceptions = IllegalArgumentException.class)
  public void testTagImageNoOptions() throws Throwable {
    // construct the service
    constructClientService();

    server.enqueue(new MockResponse());

    // Invoke operation with null options model (negative test)
    containerRegistryService.tagImage(null).execute();
  }

  @Test
  public void testDeleteImageWOptions() throws Throwable {
    // Schedule some responses.
    String mockResponseBody = "{\"Untagged\": \"untagged\"}";
    String deleteImagePath = "/api/v1/images/testString";

    server.enqueue(new MockResponse()
    .setHeader("Content-type", "application/json")
    .setResponseCode(200)
    .setBody(mockResponseBody));

    constructClientService();

    // Construct an instance of the DeleteImageOptions model
    DeleteImageOptions deleteImageOptionsModel = new DeleteImageOptions.Builder()
    .image("testString")
    .build();

    // Invoke operation with valid options model (positive test)
    Response<ImageDeleteResult> response = containerRegistryService.deleteImage(deleteImageOptionsModel).execute();
    assertNotNull(response);
    ImageDeleteResult responseObj = response.getResult();
    assertNotNull(responseObj);

    // Verify the contents of the request
    RecordedRequest request = server.takeRequest();
    assertNotNull(request);
    assertEquals(request.getMethod(), "DELETE");
    assertEquals(request.getHeader("Account"), "testString");

    // Check query
    Map<String, String> query = TestUtilities.parseQueryString(request);
    assertNull(query);

    // Check request path
    String parsedPath = TestUtilities.parseReqPath(request);
    assertEquals(parsedPath, deleteImagePath);
  }

  // Test the deleteImage operation with null options model parameter
  @Test(expectedExceptions = IllegalArgumentException.class)
  public void testDeleteImageNoOptions() throws Throwable {
    // construct the service
    constructClientService();

    server.enqueue(new MockResponse());

    // Invoke operation with null options model (negative test)
    containerRegistryService.deleteImage(null).execute();
  }

  @Test
  public void testInspectImageWOptions() throws Throwable {
    // Schedule some responses.
    String mockResponseBody = "{\"Architecture\": \"architecture\", \"Author\": \"author\", \"Comment\": \"comment\", \"Config\": {\"ArgsEscaped\": false, \"AttachStderr\": true, \"AttachStdin\": false, \"AttachStdout\": true, \"Cmd\": [\"cmd\"], \"Domainname\": \"domainname\", \"Entrypoint\": [\"entrypoint\"], \"Env\": [\"env\"], \"ExposedPorts\": {\"mapKey\": \"anyValue\"}, \"Healthcheck\": {\"Interval\": 8, \"Retries\": 7, \"Test\": [\"test\"], \"Timeout\": 7}, \"Hostname\": \"hostname\", \"Image\": \"image\", \"Labels\": {\"mapKey\": \"inner\"}, \"MacAddress\": \"macAddress\", \"NetworkDisabled\": false, \"OnBuild\": [\"onBuild\"], \"OpenStdin\": false, \"Shell\": [\"shell\"], \"StdinOnce\": false, \"StopSignal\": \"stopSignal\", \"StopTimeout\": 11, \"Tty\": false, \"User\": \"user\", \"Volumes\": {\"mapKey\": \"anyValue\"}, \"WorkingDir\": \"workingDir\"}, \"Container\": \"container\", \"ContainerConfig\": {\"ArgsEscaped\": false, \"AttachStderr\": true, \"AttachStdin\": false, \"AttachStdout\": true, \"Cmd\": [\"cmd\"], \"Domainname\": \"domainname\", \"Entrypoint\": [\"entrypoint\"], \"Env\": [\"env\"], \"ExposedPorts\": {\"mapKey\": \"anyValue\"}, \"Healthcheck\": {\"Interval\": 8, \"Retries\": 7, \"Test\": [\"test\"], \"Timeout\": 7}, \"Hostname\": \"hostname\", \"Image\": \"image\", \"Labels\": {\"mapKey\": \"inner\"}, \"MacAddress\": \"macAddress\", \"NetworkDisabled\": false, \"OnBuild\": [\"onBuild\"], \"OpenStdin\": false, \"Shell\": [\"shell\"], \"StdinOnce\": false, \"StopSignal\": \"stopSignal\", \"StopTimeout\": 11, \"Tty\": false, \"User\": \"user\", \"Volumes\": {\"mapKey\": \"anyValue\"}, \"WorkingDir\": \"workingDir\"}, \"Created\": \"created\", \"DockerVersion\": \"dockerVersion\", \"Id\": \"id\", \"ManifestType\": \"manifestType\", \"Os\": \"os\", \"OsVersion\": \"osVersion\", \"Parent\": \"parent\", \"RootFS\": {\"BaseLayer\": \"baseLayer\", \"Layers\": [\"layers\"], \"Type\": \"type\"}, \"Size\": 4, \"VirtualSize\": 11}";
    String inspectImagePath = "/api/v1/images/testString/json";

    server.enqueue(new MockResponse()
    .setHeader("Content-type", "application/json")
    .setResponseCode(200)
    .setBody(mockResponseBody));

    constructClientService();

    // Construct an instance of the InspectImageOptions model
    InspectImageOptions inspectImageOptionsModel = new InspectImageOptions.Builder()
    .image("testString")
    .build();

    // Invoke operation with valid options model (positive test)
    Response<ImageInspection> response = containerRegistryService.inspectImage(inspectImageOptionsModel).execute();
    assertNotNull(response);
    ImageInspection responseObj = response.getResult();
    assertNotNull(responseObj);

    // Verify the contents of the request
    RecordedRequest request = server.takeRequest();
    assertNotNull(request);
    assertEquals(request.getMethod(), "GET");
    assertEquals(request.getHeader("Account"), "testString");

    // Check query
    Map<String, String> query = TestUtilities.parseQueryString(request);
    assertNull(query);

    // Check request path
    String parsedPath = TestUtilities.parseReqPath(request);
    assertEquals(parsedPath, inspectImagePath);
  }

  // Test the inspectImage operation with null options model parameter
  @Test(expectedExceptions = IllegalArgumentException.class)
  public void testInspectImageNoOptions() throws Throwable {
    // construct the service
    constructClientService();

    server.enqueue(new MockResponse());

    // Invoke operation with null options model (negative test)
    containerRegistryService.inspectImage(null).execute();
  }

  @Test
  public void testGetImageManifestWOptions() throws Throwable {
    // Schedule some responses.
    String mockResponseBody = "{\"mapKey\": \"anyValue\"}";
    String getImageManifestPath = "/api/v1/images/testString/manifest";

    server.enqueue(new MockResponse()
    .setHeader("Content-type", "application/json")
    .setResponseCode(200)
    .setBody(mockResponseBody));

    constructClientService();

    // Construct an instance of the GetImageManifestOptions model
    GetImageManifestOptions getImageManifestOptionsModel = new GetImageManifestOptions.Builder()
    .image("testString")
    .build();

    // Invoke operation with valid options model (positive test)
    Response<Map<String, Object>> response = containerRegistryService.getImageManifest(getImageManifestOptionsModel).execute();
    assertNotNull(response);
    Map<String, Object> responseObj = response.getResult();
    assertNotNull(responseObj);

    // Verify the contents of the request
    RecordedRequest request = server.takeRequest();
    assertNotNull(request);
    assertEquals(request.getMethod(), "GET");
    assertEquals(request.getHeader("Account"), "testString");

    // Check query
    Map<String, String> query = TestUtilities.parseQueryString(request);
    assertNull(query);

    // Check request path
    String parsedPath = TestUtilities.parseReqPath(request);
    assertEquals(parsedPath, getImageManifestPath);
  }

  // Test the getImageManifest operation with null options model parameter
  @Test(expectedExceptions = IllegalArgumentException.class)
  public void testGetImageManifestNoOptions() throws Throwable {
    // construct the service
    constructClientService();

    server.enqueue(new MockResponse());

    // Invoke operation with null options model (negative test)
    containerRegistryService.getImageManifest(null).execute();
  }

  @Test
  public void testGetMessagesWOptions() throws Throwable {
    // Schedule some responses.
    String mockResponseBody = "\"operationResponse\"";
    String getMessagesPath = "/api/v1/messages";

    server.enqueue(new MockResponse()
    .setHeader("Content-type", "application/json")
    .setResponseCode(200)
    .setBody(mockResponseBody));

    constructClientService();

    // Construct an instance of the GetMessagesOptions model
    GetMessagesOptions getMessagesOptionsModel = new GetMessagesOptions();

    // Invoke operation with valid options model (positive test)
    Response<String> response = containerRegistryService.getMessages(getMessagesOptionsModel).execute();
    assertNotNull(response);
    String responseObj = response.getResult();
    assertNotNull(responseObj);

    // Verify the contents of the request
    RecordedRequest request = server.takeRequest();
    assertNotNull(request);
    assertEquals(request.getMethod(), "GET");

    // Check query
    Map<String, String> query = TestUtilities.parseQueryString(request);
    assertNull(query);

    // Check request path
    String parsedPath = TestUtilities.parseReqPath(request);
    assertEquals(parsedPath, getMessagesPath);
  }

  @Test
  public void testListNamespacesWOptions() throws Throwable {
    // Schedule some responses.
    String mockResponseBody = "[\"operationResponse\"]";
    String listNamespacesPath = "/api/v1/namespaces";

    server.enqueue(new MockResponse()
    .setHeader("Content-type", "application/json")
    .setResponseCode(200)
    .setBody(mockResponseBody));

    constructClientService();

    // Construct an instance of the ListNamespacesOptions model
    ListNamespacesOptions listNamespacesOptionsModel = new ListNamespacesOptions();

    // Invoke operation with valid options model (positive test)
    Response<List<String>> response = containerRegistryService.listNamespaces(listNamespacesOptionsModel).execute();
    assertNotNull(response);
    List<String> responseObj = response.getResult();
    assertNotNull(responseObj);

    // Verify the contents of the request
    RecordedRequest request = server.takeRequest();
    assertNotNull(request);
    assertEquals(request.getMethod(), "GET");
    assertEquals(request.getHeader("Account"), "testString");

    // Check query
    Map<String, String> query = TestUtilities.parseQueryString(request);
    assertNull(query);

    // Check request path
    String parsedPath = TestUtilities.parseReqPath(request);
    assertEquals(parsedPath, listNamespacesPath);
  }

  @Test
  public void testListNamespaceDetailsWOptions() throws Throwable {
    // Schedule some responses.
    String mockResponseBody = "[{\"account\": \"account\", \"created_date\": \"createdDate\", \"crn\": \"crn\", \"name\": \"name\", \"resource_created_date\": \"resourceCreatedDate\", \"resource_group\": \"resourceGroup\", \"updated_date\": \"updatedDate\"}]";
    String listNamespaceDetailsPath = "/api/v1/namespaces/details";

    server.enqueue(new MockResponse()
    .setHeader("Content-type", "application/json")
    .setResponseCode(200)
    .setBody(mockResponseBody));

    constructClientService();

    // Construct an instance of the ListNamespaceDetailsOptions model
    ListNamespaceDetailsOptions listNamespaceDetailsOptionsModel = new ListNamespaceDetailsOptions();

    // Invoke operation with valid options model (positive test)
    Response<List<NamespaceDetails>> response = containerRegistryService.listNamespaceDetails(listNamespaceDetailsOptionsModel).execute();
    assertNotNull(response);
    List<NamespaceDetails> responseObj = response.getResult();
    assertNotNull(responseObj);

    // Verify the contents of the request
    RecordedRequest request = server.takeRequest();
    assertNotNull(request);
    assertEquals(request.getMethod(), "GET");
    assertEquals(request.getHeader("Account"), "testString");

    // Check query
    Map<String, String> query = TestUtilities.parseQueryString(request);
    assertNull(query);

    // Check request path
    String parsedPath = TestUtilities.parseReqPath(request);
    assertEquals(parsedPath, listNamespaceDetailsPath);
  }

  @Test
  public void testCreateNamespaceWOptions() throws Throwable {
    // Schedule some responses.
    String mockResponseBody = "{\"namespace\": \"namespace\"}";
    String createNamespacePath = "/api/v1/namespaces/testString";

    server.enqueue(new MockResponse()
    .setHeader("Content-type", "application/json")
    .setResponseCode(200)
    .setBody(mockResponseBody));

    constructClientService();

    // Construct an instance of the CreateNamespaceOptions model
    CreateNamespaceOptions createNamespaceOptionsModel = new CreateNamespaceOptions.Builder()
    .name("testString")
    .xAuthResourceGroup("testString")
    .build();

    // Invoke operation with valid options model (positive test)
    Response<Namespace> response = containerRegistryService.createNamespace(createNamespaceOptionsModel).execute();
    assertNotNull(response);
    Namespace responseObj = response.getResult();
    assertNotNull(responseObj);

    // Verify the contents of the request
    RecordedRequest request = server.takeRequest();
    assertNotNull(request);
    assertEquals(request.getMethod(), "PUT");
    assertEquals(request.getHeader("Account"), "testString");

    // Check query
    Map<String, String> query = TestUtilities.parseQueryString(request);
    assertNull(query);

    // Check request path
    String parsedPath = TestUtilities.parseReqPath(request);
    assertEquals(parsedPath, createNamespacePath);
  }

  // Test the createNamespace operation with null options model parameter
  @Test(expectedExceptions = IllegalArgumentException.class)
  public void testCreateNamespaceNoOptions() throws Throwable {
    // construct the service
    constructClientService();

    server.enqueue(new MockResponse());

    // Invoke operation with null options model (negative test)
    containerRegistryService.createNamespace(null).execute();
  }

  @Test
  public void testAssignNamespaceWOptions() throws Throwable {
    // Schedule some responses.
    String mockResponseBody = "{\"namespace\": \"namespace\"}";
    String assignNamespacePath = "/api/v1/namespaces/testString";

    server.enqueue(new MockResponse()
    .setHeader("Content-type", "application/json")
    .setResponseCode(200)
    .setBody(mockResponseBody));

    constructClientService();

    // Construct an instance of the AssignNamespaceOptions model
    AssignNamespaceOptions assignNamespaceOptionsModel = new AssignNamespaceOptions.Builder()
    .xAuthResourceGroup("testString")
    .name("testString")
    .build();

    // Invoke operation with valid options model (positive test)
    Response<Namespace> response = containerRegistryService.assignNamespace(assignNamespaceOptionsModel).execute();
    assertNotNull(response);
    Namespace responseObj = response.getResult();
    assertNotNull(responseObj);

    // Verify the contents of the request
    RecordedRequest request = server.takeRequest();
    assertNotNull(request);
    assertEquals(request.getMethod(), "PATCH");
    assertEquals(request.getHeader("Account"), "testString");
    assertEquals(request.getHeader("X-Auth-Resource-Group"), "testString");

    // Check query
    Map<String, String> query = TestUtilities.parseQueryString(request);
    assertNull(query);

    // Check request path
    String parsedPath = TestUtilities.parseReqPath(request);
    assertEquals(parsedPath, assignNamespacePath);
  }

  // Test the assignNamespace operation with null options model parameter
  @Test(expectedExceptions = IllegalArgumentException.class)
  public void testAssignNamespaceNoOptions() throws Throwable {
    // construct the service
    constructClientService();

    server.enqueue(new MockResponse());

    // Invoke operation with null options model (negative test)
    containerRegistryService.assignNamespace(null).execute();
  }

  @Test
  public void testDeleteNamespaceWOptions() throws Throwable {
    // Schedule some responses.
    String mockResponseBody = "";
    String deleteNamespacePath = "/api/v1/namespaces/testString";

    server.enqueue(new MockResponse()
    .setResponseCode(204)
    .setBody(mockResponseBody));

    constructClientService();

    // Construct an instance of the DeleteNamespaceOptions model
    DeleteNamespaceOptions deleteNamespaceOptionsModel = new DeleteNamespaceOptions.Builder()
    .name("testString")
    .build();

    // Invoke operation with valid options model (positive test)
    Response<Void> response = containerRegistryService.deleteNamespace(deleteNamespaceOptionsModel).execute();
    assertNotNull(response);
    Void responseObj = response.getResult();
    // Response does not have a return type. Check that the result is null.
    assertNull(responseObj);

    // Verify the contents of the request
    RecordedRequest request = server.takeRequest();
    assertNotNull(request);
    assertEquals(request.getMethod(), "DELETE");
    assertEquals(request.getHeader("Account"), "testString");

    // Check query
    Map<String, String> query = TestUtilities.parseQueryString(request);
    assertNull(query);

    // Check request path
    String parsedPath = TestUtilities.parseReqPath(request);
    assertEquals(parsedPath, deleteNamespacePath);
  }

  // Test the deleteNamespace operation with null options model parameter
  @Test(expectedExceptions = IllegalArgumentException.class)
  public void testDeleteNamespaceNoOptions() throws Throwable {
    // construct the service
    constructClientService();

    server.enqueue(new MockResponse());

    // Invoke operation with null options model (negative test)
    containerRegistryService.deleteNamespace(null).execute();
  }

  @Test
  public void testGetPlansWOptions() throws Throwable {
    // Schedule some responses.
    String mockResponseBody = "{\"plan\": \"plan\"}";
    String getPlansPath = "/api/v1/plans";

    server.enqueue(new MockResponse()
    .setHeader("Content-type", "application/json")
    .setResponseCode(200)
    .setBody(mockResponseBody));

    constructClientService();

    // Construct an instance of the GetPlansOptions model
    GetPlansOptions getPlansOptionsModel = new GetPlansOptions();

    // Invoke operation with valid options model (positive test)
    Response<Plan> response = containerRegistryService.getPlans(getPlansOptionsModel).execute();
    assertNotNull(response);
    Plan responseObj = response.getResult();
    assertNotNull(responseObj);

    // Verify the contents of the request
    RecordedRequest request = server.takeRequest();
    assertNotNull(request);
    assertEquals(request.getMethod(), "GET");
    assertEquals(request.getHeader("Account"), "testString");

    // Check query
    Map<String, String> query = TestUtilities.parseQueryString(request);
    assertNull(query);

    // Check request path
    String parsedPath = TestUtilities.parseReqPath(request);
    assertEquals(parsedPath, getPlansPath);
  }

  @Test
  public void testUpdatePlansWOptions() throws Throwable {
    // Schedule some responses.
    String mockResponseBody = "";
    String updatePlansPath = "/api/v1/plans";

    server.enqueue(new MockResponse()
    .setResponseCode(200)
    .setBody(mockResponseBody));

    constructClientService();

    // Construct an instance of the UpdatePlansOptions model
    UpdatePlansOptions updatePlansOptionsModel = new UpdatePlansOptions.Builder()
    .plan("Standard")
    .build();

    // Invoke operation with valid options model (positive test)
    Response<Void> response = containerRegistryService.updatePlans(updatePlansOptionsModel).execute();
    assertNotNull(response);
    Void responseObj = response.getResult();
    // Response does not have a return type. Check that the result is null.
    assertNull(responseObj);

    // Verify the contents of the request
    RecordedRequest request = server.takeRequest();
    assertNotNull(request);
    assertEquals(request.getMethod(), "PATCH");
    assertEquals(request.getHeader("Account"), "testString");

    // Check query
    Map<String, String> query = TestUtilities.parseQueryString(request);
    assertNull(query);

    // Check request path
    String parsedPath = TestUtilities.parseReqPath(request);
    assertEquals(parsedPath, updatePlansPath);
  }

  @Test
  public void testGetQuotaWOptions() throws Throwable {
    // Schedule some responses.
    String mockResponseBody = "{\"limit\": {\"storage_bytes\": 12, \"traffic_bytes\": 12}, \"usage\": {\"storage_bytes\": 12, \"traffic_bytes\": 12}}";
    String getQuotaPath = "/api/v1/quotas";

    server.enqueue(new MockResponse()
    .setHeader("Content-type", "application/json")
    .setResponseCode(200)
    .setBody(mockResponseBody));

    constructClientService();

    // Construct an instance of the GetQuotaOptions model
    GetQuotaOptions getQuotaOptionsModel = new GetQuotaOptions();

    // Invoke operation with valid options model (positive test)
    Response<Quota> response = containerRegistryService.getQuota(getQuotaOptionsModel).execute();
    assertNotNull(response);
    Quota responseObj = response.getResult();
    assertNotNull(responseObj);

    // Verify the contents of the request
    RecordedRequest request = server.takeRequest();
    assertNotNull(request);
    assertEquals(request.getMethod(), "GET");
    assertEquals(request.getHeader("Account"), "testString");

    // Check query
    Map<String, String> query = TestUtilities.parseQueryString(request);
    assertNull(query);

    // Check request path
    String parsedPath = TestUtilities.parseReqPath(request);
    assertEquals(parsedPath, getQuotaPath);
  }

  @Test
  public void testUpdateQuotaWOptions() throws Throwable {
    // Schedule some responses.
    String mockResponseBody = "";
    String updateQuotaPath = "/api/v1/quotas";

    server.enqueue(new MockResponse()
    .setResponseCode(200)
    .setBody(mockResponseBody));

    constructClientService();

    // Construct an instance of the UpdateQuotaOptions model
    UpdateQuotaOptions updateQuotaOptionsModel = new UpdateQuotaOptions.Builder()
    .storageMegabytes(Long.valueOf("26"))
    .trafficMegabytes(Long.valueOf("480"))
    .build();

    // Invoke operation with valid options model (positive test)
    Response<Void> response = containerRegistryService.updateQuota(updateQuotaOptionsModel).execute();
    assertNotNull(response);
    Void responseObj = response.getResult();
    // Response does not have a return type. Check that the result is null.
    assertNull(responseObj);

    // Verify the contents of the request
    RecordedRequest request = server.takeRequest();
    assertNotNull(request);
    assertEquals(request.getMethod(), "PATCH");
    assertEquals(request.getHeader("Account"), "testString");

    // Check query
    Map<String, String> query = TestUtilities.parseQueryString(request);
    assertNull(query);

    // Check request path
    String parsedPath = TestUtilities.parseReqPath(request);
    assertEquals(parsedPath, updateQuotaPath);
  }

  @Test
  public void testListRetentionPoliciesWOptions() throws Throwable {
    // Schedule some responses.
    String mockResponseBody = "{\"mapKey\": {\"images_per_repo\": 13, \"namespace\": \"namespace\", \"retain_untagged\": true}}";
    String listRetentionPoliciesPath = "/api/v1/retentions";

    server.enqueue(new MockResponse()
    .setHeader("Content-type", "application/json")
    .setResponseCode(200)
    .setBody(mockResponseBody));

    constructClientService();

    // Construct an instance of the ListRetentionPoliciesOptions model
    ListRetentionPoliciesOptions listRetentionPoliciesOptionsModel = new ListRetentionPoliciesOptions();

    // Invoke operation with valid options model (positive test)
    Response<Map<String, RetentionPolicy>> response = containerRegistryService.listRetentionPolicies(listRetentionPoliciesOptionsModel).execute();
    assertNotNull(response);
    Map<String, RetentionPolicy> responseObj = response.getResult();
    assertNotNull(responseObj);

    // Verify the contents of the request
    RecordedRequest request = server.takeRequest();
    assertNotNull(request);
    assertEquals(request.getMethod(), "GET");
    assertEquals(request.getHeader("Account"), "testString");

    // Check query
    Map<String, String> query = TestUtilities.parseQueryString(request);
    assertNull(query);

    // Check request path
    String parsedPath = TestUtilities.parseReqPath(request);
    assertEquals(parsedPath, listRetentionPoliciesPath);
  }

  @Test
  public void testSetRetentionPolicyWOptions() throws Throwable {
    // Schedule some responses.
    String mockResponseBody = "";
    String setRetentionPolicyPath = "/api/v1/retentions";

    server.enqueue(new MockResponse()
    .setResponseCode(200)
    .setBody(mockResponseBody));

    constructClientService();

    // Construct an instance of the SetRetentionPolicyOptions model
    SetRetentionPolicyOptions setRetentionPolicyOptionsModel = new SetRetentionPolicyOptions.Builder()
    .namespace("birds")
    .imagesPerRepo(Long.valueOf("10"))
    .retainUntagged(false)
    .build();

    // Invoke operation with valid options model (positive test)
    Response<Void> response = containerRegistryService.setRetentionPolicy(setRetentionPolicyOptionsModel).execute();
    assertNotNull(response);
    Void responseObj = response.getResult();
    // Response does not have a return type. Check that the result is null.
    assertNull(responseObj);

    // Verify the contents of the request
    RecordedRequest request = server.takeRequest();
    assertNotNull(request);
    assertEquals(request.getMethod(), "POST");
    assertEquals(request.getHeader("Account"), "testString");

    // Check query
    Map<String, String> query = TestUtilities.parseQueryString(request);
    assertNull(query);

    // Check request path
    String parsedPath = TestUtilities.parseReqPath(request);
    assertEquals(parsedPath, setRetentionPolicyPath);
  }

  // Test the setRetentionPolicy operation with null options model parameter
  @Test(expectedExceptions = IllegalArgumentException.class)
  public void testSetRetentionPolicyNoOptions() throws Throwable {
    // construct the service
    constructClientService();

    server.enqueue(new MockResponse());

    // Invoke operation with null options model (negative test)
    containerRegistryService.setRetentionPolicy(null).execute();
  }

  @Test
  public void testAnalyzeRetentionPolicyWOptions() throws Throwable {
    // Schedule some responses.
    String mockResponseBody = "{\"mapKey\": [\"inner\"]}";
    String analyzeRetentionPolicyPath = "/api/v1/retentions/analyze";

    server.enqueue(new MockResponse()
    .setHeader("Content-type", "application/json")
    .setResponseCode(200)
    .setBody(mockResponseBody));

    constructClientService();

    // Construct an instance of the AnalyzeRetentionPolicyOptions model
    AnalyzeRetentionPolicyOptions analyzeRetentionPolicyOptionsModel = new AnalyzeRetentionPolicyOptions.Builder()
    .namespace("birds")
    .imagesPerRepo(Long.valueOf("10"))
    .retainUntagged(false)
    .build();

    // Invoke operation with valid options model (positive test)
    Response<Map<String, List<String>>> response = containerRegistryService.analyzeRetentionPolicy(analyzeRetentionPolicyOptionsModel).execute();
    assertNotNull(response);
    Map<String, List<String>> responseObj = response.getResult();
    assertNotNull(responseObj);

    // Verify the contents of the request
    RecordedRequest request = server.takeRequest();
    assertNotNull(request);
    assertEquals(request.getMethod(), "POST");
    assertEquals(request.getHeader("Account"), "testString");

    // Check query
    Map<String, String> query = TestUtilities.parseQueryString(request);
    assertNull(query);

    // Check request path
    String parsedPath = TestUtilities.parseReqPath(request);
    assertEquals(parsedPath, analyzeRetentionPolicyPath);
  }

  // Test the analyzeRetentionPolicy operation with null options model parameter
  @Test(expectedExceptions = IllegalArgumentException.class)
  public void testAnalyzeRetentionPolicyNoOptions() throws Throwable {
    // construct the service
    constructClientService();

    server.enqueue(new MockResponse());

    // Invoke operation with null options model (negative test)
    containerRegistryService.analyzeRetentionPolicy(null).execute();
  }

  @Test
  public void testGetRetentionPolicyWOptions() throws Throwable {
    // Schedule some responses.
    String mockResponseBody = "{\"images_per_repo\": 13, \"namespace\": \"namespace\", \"retain_untagged\": true}";
    String getRetentionPolicyPath = "/api/v1/retentions/testString";

    server.enqueue(new MockResponse()
    .setHeader("Content-type", "application/json")
    .setResponseCode(200)
    .setBody(mockResponseBody));

    constructClientService();

    // Construct an instance of the GetRetentionPolicyOptions model
    GetRetentionPolicyOptions getRetentionPolicyOptionsModel = new GetRetentionPolicyOptions.Builder()
    .namespace("testString")
    .build();

    // Invoke operation with valid options model (positive test)
    Response<RetentionPolicy> response = containerRegistryService.getRetentionPolicy(getRetentionPolicyOptionsModel).execute();
    assertNotNull(response);
    RetentionPolicy responseObj = response.getResult();
    assertNotNull(responseObj);

    // Verify the contents of the request
    RecordedRequest request = server.takeRequest();
    assertNotNull(request);
    assertEquals(request.getMethod(), "GET");
    assertEquals(request.getHeader("Account"), "testString");

    // Check query
    Map<String, String> query = TestUtilities.parseQueryString(request);
    assertNull(query);

    // Check request path
    String parsedPath = TestUtilities.parseReqPath(request);
    assertEquals(parsedPath, getRetentionPolicyPath);
  }

  // Test the getRetentionPolicy operation with null options model parameter
  @Test(expectedExceptions = IllegalArgumentException.class)
  public void testGetRetentionPolicyNoOptions() throws Throwable {
    // construct the service
    constructClientService();

    server.enqueue(new MockResponse());

    // Invoke operation with null options model (negative test)
    containerRegistryService.getRetentionPolicy(null).execute();
  }

  @Test
  public void testGetSettingsWOptions() throws Throwable {
    // Schedule some responses.
    String mockResponseBody = "{\"platform_metrics\": false}";
    String getSettingsPath = "/api/v1/settings";

    server.enqueue(new MockResponse()
    .setHeader("Content-type", "application/json")
    .setResponseCode(200)
    .setBody(mockResponseBody));

    constructClientService();

    // Construct an instance of the GetSettingsOptions model
    GetSettingsOptions getSettingsOptionsModel = new GetSettingsOptions();

    // Invoke operation with valid options model (positive test)
    Response<AccountSettings> response = containerRegistryService.getSettings(getSettingsOptionsModel).execute();
    assertNotNull(response);
    AccountSettings responseObj = response.getResult();
    assertNotNull(responseObj);

    // Verify the contents of the request
    RecordedRequest request = server.takeRequest();
    assertNotNull(request);
    assertEquals(request.getMethod(), "GET");
    assertEquals(request.getHeader("Account"), "testString");

    // Check query
    Map<String, String> query = TestUtilities.parseQueryString(request);
    assertNull(query);

    // Check request path
    String parsedPath = TestUtilities.parseReqPath(request);
    assertEquals(parsedPath, getSettingsPath);
  }

  @Test
  public void testUpdateSettingsWOptions() throws Throwable {
    // Schedule some responses.
    String mockResponseBody = "";
    String updateSettingsPath = "/api/v1/settings";

    server.enqueue(new MockResponse()
    .setResponseCode(204)
    .setBody(mockResponseBody));

    constructClientService();

    // Construct an instance of the UpdateSettingsOptions model
    UpdateSettingsOptions updateSettingsOptionsModel = new UpdateSettingsOptions.Builder()
    .platformMetrics(true)
    .build();

    // Invoke operation with valid options model (positive test)
    Response<Void> response = containerRegistryService.updateSettings(updateSettingsOptionsModel).execute();
    assertNotNull(response);
    Void responseObj = response.getResult();
    // Response does not have a return type. Check that the result is null.
    assertNull(responseObj);

    // Verify the contents of the request
    RecordedRequest request = server.takeRequest();
    assertNotNull(request);
    assertEquals(request.getMethod(), "PATCH");
    assertEquals(request.getHeader("Account"), "testString");

    // Check query
    Map<String, String> query = TestUtilities.parseQueryString(request);
    assertNull(query);

    // Check request path
    String parsedPath = TestUtilities.parseReqPath(request);
    assertEquals(parsedPath, updateSettingsPath);
  }

  @Test
  public void testDeleteImageTagWOptions() throws Throwable {
    // Schedule some responses.
    String mockResponseBody = "{\"Untagged\": \"untagged\"}";
    String deleteImageTagPath = "/api/v1/tags/testString";

    server.enqueue(new MockResponse()
    .setHeader("Content-type", "application/json")
    .setResponseCode(200)
    .setBody(mockResponseBody));

    constructClientService();

    // Construct an instance of the DeleteImageTagOptions model
    DeleteImageTagOptions deleteImageTagOptionsModel = new DeleteImageTagOptions.Builder()
    .image("testString")
    .build();

    // Invoke operation with valid options model (positive test)
    Response<ImageDeleteResult> response = containerRegistryService.deleteImageTag(deleteImageTagOptionsModel).execute();
    assertNotNull(response);
    ImageDeleteResult responseObj = response.getResult();
    assertNotNull(responseObj);

    // Verify the contents of the request
    RecordedRequest request = server.takeRequest();
    assertNotNull(request);
    assertEquals(request.getMethod(), "DELETE");
    assertEquals(request.getHeader("Account"), "testString");

    // Check query
    Map<String, String> query = TestUtilities.parseQueryString(request);
    assertNull(query);

    // Check request path
    String parsedPath = TestUtilities.parseReqPath(request);
    assertEquals(parsedPath, deleteImageTagPath);
  }

  // Test the deleteImageTag operation with null options model parameter
  @Test(expectedExceptions = IllegalArgumentException.class)
  public void testDeleteImageTagNoOptions() throws Throwable {
    // construct the service
    constructClientService();

    server.enqueue(new MockResponse());

    // Invoke operation with null options model (negative test)
    containerRegistryService.deleteImageTag(null).execute();
  }

  @Test
  public void testListDeletedImagesWOptions() throws Throwable {
    // Schedule some responses.
    String mockResponseBody = "{\"mapKey\": {\"daysUntilExpiry\": 15, \"tags\": [\"tags\"]}}";
    String listDeletedImagesPath = "/api/v1/trash";

    server.enqueue(new MockResponse()
    .setHeader("Content-type", "application/json")
    .setResponseCode(200)
    .setBody(mockResponseBody));

    constructClientService();

    // Construct an instance of the ListDeletedImagesOptions model
    ListDeletedImagesOptions listDeletedImagesOptionsModel = new ListDeletedImagesOptions.Builder()
    .namespace("testString")
    .build();

    // Invoke operation with valid options model (positive test)
    Response<Map<String, Trash>> response = containerRegistryService.listDeletedImages(listDeletedImagesOptionsModel).execute();
    assertNotNull(response);
    Map<String, Trash> responseObj = response.getResult();
    assertNotNull(responseObj);

    // Verify the contents of the request
    RecordedRequest request = server.takeRequest();
    assertNotNull(request);
    assertEquals(request.getMethod(), "GET");
    assertEquals(request.getHeader("Account"), "testString");

    // Check query
    Map<String, String> query = TestUtilities.parseQueryString(request);
    assertNotNull(query);
    // Get query params
    assertEquals(query.get("namespace"), "testString");
    // Check request path
    String parsedPath = TestUtilities.parseReqPath(request);
    assertEquals(parsedPath, listDeletedImagesPath);
  }

  @Test
  public void testRestoreTagsWOptions() throws Throwable {
    // Schedule some responses.
    String mockResponseBody = "{\"successful\": [\"successful\"], \"unsuccessful\": [\"unsuccessful\"]}";
    String restoreTagsPath = "/api/v1/trash/testString/restoretags";

    server.enqueue(new MockResponse()
    .setHeader("Content-type", "application/json")
    .setResponseCode(200)
    .setBody(mockResponseBody));

    constructClientService();

    // Construct an instance of the RestoreTagsOptions model
    RestoreTagsOptions restoreTagsOptionsModel = new RestoreTagsOptions.Builder()
    .digest("testString")
    .build();

    // Invoke operation with valid options model (positive test)
    Response<RestoreResult> response = containerRegistryService.restoreTags(restoreTagsOptionsModel).execute();
    assertNotNull(response);
    RestoreResult responseObj = response.getResult();
    assertNotNull(responseObj);

    // Verify the contents of the request
    RecordedRequest request = server.takeRequest();
    assertNotNull(request);
    assertEquals(request.getMethod(), "POST");
    assertEquals(request.getHeader("Account"), "testString");

    // Check query
    Map<String, String> query = TestUtilities.parseQueryString(request);
    assertNull(query);

    // Check request path
    String parsedPath = TestUtilities.parseReqPath(request);
    assertEquals(parsedPath, restoreTagsPath);
  }

  // Test the restoreTags operation with null options model parameter
  @Test(expectedExceptions = IllegalArgumentException.class)
  public void testRestoreTagsNoOptions() throws Throwable {
    // construct the service
    constructClientService();

    server.enqueue(new MockResponse());

    // Invoke operation with null options model (negative test)
    containerRegistryService.restoreTags(null).execute();
  }

  @Test
  public void testRestoreImageWOptions() throws Throwable {
    // Schedule some responses.
    String mockResponseBody = "";
    String restoreImagePath = "/api/v1/trash/testString/restore";

    server.enqueue(new MockResponse()
    .setResponseCode(200)
    .setBody(mockResponseBody));

    constructClientService();

    // Construct an instance of the RestoreImageOptions model
    RestoreImageOptions restoreImageOptionsModel = new RestoreImageOptions.Builder()
    .image("testString")
    .build();

    // Invoke operation with valid options model (positive test)
    Response<Void> response = containerRegistryService.restoreImage(restoreImageOptionsModel).execute();
    assertNotNull(response);
    Void responseObj = response.getResult();
    // Response does not have a return type. Check that the result is null.
    assertNull(responseObj);

    // Verify the contents of the request
    RecordedRequest request = server.takeRequest();
    assertNotNull(request);
    assertEquals(request.getMethod(), "POST");
    assertEquals(request.getHeader("Account"), "testString");

    // Check query
    Map<String, String> query = TestUtilities.parseQueryString(request);
    assertNull(query);

    // Check request path
    String parsedPath = TestUtilities.parseReqPath(request);
    assertEquals(parsedPath, restoreImagePath);
  }

  // Test the restoreImage operation with null options model parameter
  @Test(expectedExceptions = IllegalArgumentException.class)
  public void testRestoreImageNoOptions() throws Throwable {
    // construct the service
    constructClientService();

    server.enqueue(new MockResponse());

    // Invoke operation with null options model (negative test)
    containerRegistryService.restoreImage(null).execute();
  }

  /** Initialize the server */
  @BeforeMethod
  public void setUpMockServer() {
    try {
        server = new MockWebServer();
        // register handler
        server.start();
        }
    catch (IOException err) {
        fail("Failed to instantiate mock web server");
    }
  }

  @AfterMethod
  public void tearDownMockServer() throws IOException {
    server.shutdown();
    containerRegistryService = null;
  }
}