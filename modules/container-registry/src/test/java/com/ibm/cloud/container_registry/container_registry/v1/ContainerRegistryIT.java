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
import com.ibm.cloud.container_registry.test.SdkIntegrationTestBase;
import com.ibm.cloud.sdk.core.http.Response;
import com.ibm.cloud.sdk.core.service.exception.ServiceResponseException;
import com.ibm.cloud.sdk.core.service.model.FileWithMetadata;
import com.ibm.cloud.sdk.core.util.CredentialUtils;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
//import org.testng.TestNG;
import static org.testng.Assert.*;

/**
 * Integration test class for the ContainerRegistry service.
 */
public class ContainerRegistryIT extends SdkIntegrationTestBase {
  public ContainerRegistry service = null;
  public static Map<String, String> config = null;
  final HashMap<String, InputStream> mockStreamMap = TestUtilities.createMockStreamMap();
  final List<FileWithMetadata> mockListFileWithMetadata = TestUtilities.creatMockListFileWithMetadata();

  // Globlal variables to hold link values
  String namespaceLink = null;

  // Global variables to hold required parameters for running the unit tests
  String account = null;
  String resourceGroup = null;
  String seedImage = null;
  String seedDigest = null;
  String cfgNamespace = null;
  String dnsName = null;

  /**
   * This method provides our config filename to the base class.
   */

  public String getConfigFilename() {
    return "../../container_registry_v1.env";
  }

  @BeforeClass
  public void constructService() {
    // Ask super if we should skip the tests.
    if (skipTests()) {
      return;
    }

    // Load up our test-specific config properties.
    config = CredentialUtils.getServiceProperties(ContainerRegistry.DEFAULT_SERVICE_NAME);
    assertNotNull(config);
    assertFalse(config.isEmpty());
    account = config.get("ACCOUNT_ID");
    resourceGroup = config.get("RESOURCE_GROUP_ID");
    seedImage = config.get("SEED_IMAGE");
    seedDigest = config.get("SEED_DIGEST");
    cfgNamespace = config.get("NAMESPACE");
    dnsName = config.get("URL");
    assertNotNull(account);
    assertNotNull(resourceGroup);
    assertNotNull(seedImage);
    assertNotNull(seedDigest);
    assertNotNull(cfgNamespace);
    assertNotNull(dnsName);

    service = ContainerRegistry.newInstance(account);
    assertNotNull(service);
    assertNotNull(service.getServiceUrl());
    assertEquals(service.getServiceUrl(), config.get("URL"));

    // Derive the DNS name without the leading "https://"
    dnsName = dnsName.replaceAll("https://","");

    System.out.println("Setup complete.");
  }

  @Test
  public void testCreateNamespace() throws Exception {
    try {
      CreateNamespaceOptions createNamespaceOptions = new CreateNamespaceOptions.Builder()
      .name(cfgNamespace)
      .build();

      // Invoke operation
      Response<Namespace> response = service.createNamespace(createNamespaceOptions).execute();
      // Validate response
      assertNotNull(response);
      assertTrue((response.getStatusCode() == 200) || (response.getStatusCode() == 201));

      Namespace namespaceResult = response.getResult();

      assertNotNull(namespaceResult);
      namespaceLink = namespaceResult.getNamespace();
    } catch (ServiceResponseException e) {
        fail(String.format("Service returned status code %d: %s\nError details: %s",
          e.getStatusCode(), e.getMessage(), e.getDebuggingInfo()));
    }
  }

  @Test(dependsOnMethods = { "testCreateNamespace" })
  public void testGetAuth() throws Exception {
    try {
      GetAuthOptions getAuthOptions = new GetAuthOptions();

      // Invoke operation
      Response<AuthOptions> response = service.getAuth(getAuthOptions).execute();
      // Validate response
      assertNotNull(response);
      assertEquals(response.getStatusCode(), 200);

      AuthOptions authOptionsResult = response.getResult();

      assertNotNull(authOptionsResult);
    } catch (ServiceResponseException e) {
        fail(String.format("Service returned status code %d: %s\nError details: %s",
          e.getStatusCode(), e.getMessage(), e.getDebuggingInfo()));
    }
  }

  @Test(dependsOnMethods = { "testGetAuth" })
  public void testUpdateAuth() throws Exception {
    try {
      UpdateAuthOptions updateAuthOptions = new UpdateAuthOptions.Builder()
      .iamAuthz(true)
      .build();

      // Invoke operation
      Response<Void> response = service.updateAuth(updateAuthOptions).execute();
      // Validate response
      assertNotNull(response);
      assertEquals(response.getStatusCode(), 204);
    } catch (ServiceResponseException e) {
        fail(String.format("Service returned status code %d: %s\nError details: %s",
          e.getStatusCode(), e.getMessage(), e.getDebuggingInfo()));
    }
  }

  @Test(dependsOnMethods = { "testUpdateAuth" })
  public void testGetSettings() throws Exception {
    try {
      GetSettingsOptions getSettingsOptions = new GetSettingsOptions();

      // Invoke operation
      Response<AccountSettings> response = service.getSettings(getSettingsOptions).execute();
      // Validate response
      assertNotNull(response);
      assertEquals(response.getStatusCode(), 200);

      AccountSettings accountSettingsResult = response.getResult();

      assertNotNull(accountSettingsResult);
    } catch (ServiceResponseException e) {
        fail(String.format("Service returned status code %d: %s\nError details: %s",
          e.getStatusCode(), e.getMessage(), e.getDebuggingInfo()));
    }
  }

  @Test(dependsOnMethods = { "testGetSettings" })
  public void testUpdateSettings() throws Exception {
    try {
      UpdateSettingsOptions updateSettingsOptions = new UpdateSettingsOptions.Builder()
      .platformMetrics(false)
      .build();

      // Invoke operation
      Response<Void> response = service.updateSettings(updateSettingsOptions).execute();
      // Validate response
      assertNotNull(response);
      assertEquals(response.getStatusCode(), 204);
    } catch (ServiceResponseException e) {
        fail(String.format("Service returned status code %d: %s\nError details: %s",
          e.getStatusCode(), e.getMessage(), e.getDebuggingInfo()));
    }
  }

  @Test(dependsOnMethods = { "testUpdateSettings" })
  public void testTagImage() throws Exception {
    try {
      TagImageOptions tagImageOptions = new TagImageOptions.Builder()
      .fromimage(dnsName+"/"+seedImage)
      .toimage(dnsName+"/"+namespaceLink+"/sdktest:1")
      .build();

      // Invoke operation
      Response<Void> response = service.tagImage(tagImageOptions).execute();
      // Validate response
      assertNotNull(response);
      assertEquals(response.getStatusCode(), 201);
    } catch (ServiceResponseException e) {
        fail(String.format("Service returned status code %d: %s\nError details: %s",
          e.getStatusCode(), e.getMessage(), e.getDebuggingInfo()));
    }
  }

  @Test(dependsOnMethods = { "testTagImage" })
  public void testListImages() throws Exception {
    try {
      ListImagesOptions listImagesOptions = new ListImagesOptions.Builder()
      .namespace(namespaceLink)
      .includeIbm(false)
      .includePrivate(true)
      .includeManifestLists(true)
      .vulnerabilities(true)
      .build();

      // Invoke operation
      Response<List<RemoteAPIImage>> response = service.listImages(listImagesOptions).execute();
      // Validate response
      assertNotNull(response);
      assertEquals(response.getStatusCode(), 200);

      List<RemoteAPIImage> listRemoteApiImageResult = response.getResult();

      Boolean found = false;
      for (RemoteAPIImage image : listRemoteApiImageResult) {
        List<String> repoTags = image.getRepoTags();
        for (String tag : repoTags) {
          if (tag.equals(dnsName+"/"+namespaceLink+"/sdktest:1")) {
            found = true;
          }
        }
      }
      assertTrue(found);
    } catch (ServiceResponseException e) {
        fail(String.format("Service returned status code %d: %s\nError details: %s",
          e.getStatusCode(), e.getMessage(), e.getDebuggingInfo()));
    }
  }

  @Test(dependsOnMethods = { "testListImages" })
  public void testBulkDeleteImages() throws Exception {
    try {
      BulkDeleteImagesOptions bulkDeleteImagesOptions = new BulkDeleteImagesOptions.Builder()
      .bulkDelete(new java.util.ArrayList<String>(java.util.Arrays.asList(dnsName+"/"+namespaceLink+"/notexist:1")))
      .build();

      // Invoke operation
      Response<ImageBulkDeleteResult> response = service.bulkDeleteImages(bulkDeleteImagesOptions).execute();
      // Validate response
      assertNotNull(response);
      assertEquals(response.getStatusCode(), 200);

      ImageBulkDeleteResult imageBulkDeleteResultResult = response.getResult();

      assertNotNull(imageBulkDeleteResultResult);
      
    } catch (ServiceResponseException e) {
        fail(String.format("Service returned status code %d: %s\nError details: %s",
          e.getStatusCode(), e.getMessage(), e.getDebuggingInfo()));
    }
  }

  @Test(dependsOnMethods = { "testBulkDeleteImages" })
  public void testListImageDigests() throws Exception {
    try {
      ListImageDigestsOptions listImageDigestsOptions = new ListImageDigestsOptions.Builder()
      .excludeTagged(false)
      .excludeVa(false)
      .includeIbm(false)
      .build();

      // Invoke operation
      Response<List<ImageDigest>> response = service.listImageDigests(listImageDigestsOptions).execute();
      // Validate response
      assertNotNull(response);
      assertEquals(response.getStatusCode(), 200);

      List<ImageDigest> listImageDigestResult = response.getResult();

      assertNotNull(listImageDigestResult);

      Boolean found = false;
      for (ImageDigest image : listImageDigestResult) {
        Map<String, Map<String, VAReport>> repoTags = image.getRepoTags();
        String lookupKey = dnsName+"/"+namespaceLink+"/sdktest";
        if (repoTags.containsKey(lookupKey)) {
          found = true;
          Map<String, VAReport> repo = repoTags.get(lookupKey);
          VAReport report = repo.get("1");
          assertNotNull(report);
          assertEquals(report.getConfigurationIssueCount(), new Long(0));
        }
      }
      assertTrue(found);
    } catch (ServiceResponseException e) {
        fail(String.format("Service returned status code %d: %s\nError details: %s",
          e.getStatusCode(), e.getMessage(), e.getDebuggingInfo()));
    }
  }

  @Test(dependsOnMethods = { "testListImageDigests" })
  public void testInspectImage() throws Exception {
    try {
      InspectImageOptions inspectImageOptions = new InspectImageOptions.Builder()
      .image(dnsName+"/"+namespaceLink+"/sdktest:1")
      .build();

      // Invoke operation
      Response<ImageInspection> response = service.inspectImage(inspectImageOptions).execute();
      // Validate response
      assertNotNull(response);
      assertEquals(response.getStatusCode(), 200);

      ImageInspection imageInspectionResult = response.getResult();

      assertNotNull(imageInspectionResult);
    } catch (ServiceResponseException e) {
        fail(String.format("Service returned status code %d: %s\nError details: %s",
          e.getStatusCode(), e.getMessage(), e.getDebuggingInfo()));
    }
  }

  @Test(dependsOnMethods = { "testInspectImage" })
  public void testGetImageManifest() throws Exception {
    try {
      GetImageManifestOptions getImageManifestOptions = new GetImageManifestOptions.Builder()
      .image(dnsName+"/"+namespaceLink+"/sdktest:1")
      .build();

      // Invoke operation
      Response<Map<String, Object>> response = service.getImageManifest(getImageManifestOptions).execute();
      // Validate response
      assertNotNull(response);
      assertEquals(response.getStatusCode(), 200);

      Map<String, Object> resultResult = response.getResult();

      assertNotNull(resultResult);

      double version = ((Number)resultResult.get("schemaVersion")).doubleValue();
      double expected = 2;
      assertEquals(version, expected);
    } catch (ServiceResponseException e) {
        fail(String.format("Service returned status code %d: %s\nError details: %s",
          e.getStatusCode(), e.getMessage(), e.getDebuggingInfo()));
    }
  }

  @Test
  public void testGetMessages() throws Exception {
    try {
      GetMessagesOptions getMessagesOptions = new GetMessagesOptions();

      // Invoke operation
      Response<String> response = service.getMessages(getMessagesOptions).execute();
      // Validate response
      assertNotNull(response);
      assertTrue((response.getStatusCode() == 200) || (response.getStatusCode() == 204));

      String resultResult = response.getResult();

      assertNotNull(resultResult);
    } catch (ServiceResponseException e) {
        fail(String.format("Service returned status code %d: %s\nError details: %s",
          e.getStatusCode(), e.getMessage(), e.getDebuggingInfo()));
    }
  }

  @Test(dependsOnMethods = { "testGetImageManifest" })
  public void testListNamespaces() throws Exception {
    try {
      ListNamespacesOptions listNamespacesOptions = new ListNamespacesOptions();

      // Invoke operation
      Response<List<String>> response = service.listNamespaces(listNamespacesOptions).execute();
      // Validate response
      assertNotNull(response);
      assertEquals(response.getStatusCode(), 200);

      List<String> resultResult = response.getResult();

      assertTrue(resultResult.contains(namespaceLink));
    } catch (ServiceResponseException e) {
        fail(String.format("Service returned status code %d: %s\nError details: %s",
          e.getStatusCode(), e.getMessage(), e.getDebuggingInfo()));
    }
  }

  @Test(dependsOnMethods = { "testListNamespaces" })
  public void testListNamespaceDetails() throws Exception {
    try {
      ListNamespaceDetailsOptions listNamespaceDetailsOptions = new ListNamespaceDetailsOptions();

      // Invoke operation
      Response<List<NamespaceDetails>> response = service.listNamespaceDetails(listNamespaceDetailsOptions).execute();
      // Validate response
      assertNotNull(response);
      assertEquals(response.getStatusCode(), 200);

      List<NamespaceDetails> listNamespaceDetailsResult = response.getResult();

      assertNotNull(listNamespaceDetailsResult);
    } catch (ServiceResponseException e) {
        fail(String.format("Service returned status code %d: %s\nError details: %s",
          e.getStatusCode(), e.getMessage(), e.getDebuggingInfo()));
    }
  }

  @Test(dependsOnMethods = { "testListNamespaceDetails" })
  public void testAssignNamespace() throws Exception {
    try {
      AssignNamespaceOptions assignNamespaceOptions = new AssignNamespaceOptions.Builder()
      .xAuthResourceGroup(resourceGroup)
      .name(namespaceLink)
      .build();

      // Invoke operation
      Response<Namespace> response = service.assignNamespace(assignNamespaceOptions).execute();
      // Validate response
      assertNotNull(response);
      assertEquals(response.getStatusCode(), 200);

      Namespace namespaceResult = response.getResult();

      assertNotNull(namespaceResult);
    } catch (ServiceResponseException e) {
        fail(String.format("Service returned status code %d: %s\nError details: %s",
          e.getStatusCode(), e.getMessage(), e.getDebuggingInfo()));
    }
  }

  @Test(dependsOnMethods = { "testAssignNamespace" })
  public void testGetPlans() throws Exception {
    try {
      GetPlansOptions getPlansOptions = new GetPlansOptions();

      // Invoke operation
      Response<Plan> response = service.getPlans(getPlansOptions).execute();
      // Validate response
      assertNotNull(response);
      assertEquals(response.getStatusCode(), 200);

      Plan planResult = response.getResult();

      assertNotNull(planResult);
    } catch (ServiceResponseException e) {
        fail(String.format("Service returned status code %d: %s\nError details: %s",
          e.getStatusCode(), e.getMessage(), e.getDebuggingInfo()));
    }
  }

  // Upgrading plan affects the whole account. Not safe to attempt in this context, skipping tests...
  // @Test(dependsOnMethods = { "testGetPlans" })
  // public void testUpdatePlans() throws Exception {
  //   try {
  //     UpdatePlansOptions updatePlansOptions = new UpdatePlansOptions.Builder()
  //     .plan("Standard")
  //     .build();

  //     // Invoke operation
  //     Response<Void> response = service.updatePlans(updatePlansOptions).execute();
  //     // Validate response
  //     assertNotNull(response);
  //     assertEquals(response.getStatusCode(), 200);
  //   } catch (ServiceResponseException e) {
  //       fail(String.format("Service returned status code %d: %s\nError details: %s",
  //         e.getStatusCode(), e.getMessage(), e.getDebuggingInfo()));
  //   }
  // }

  @Test(dependsOnMethods = { "testGetPlans" })
  public void testUpdateQuota() throws Exception {
    try {
      UpdateQuotaOptions updateQuotaOptions = new UpdateQuotaOptions.Builder()
      .storageMegabytes(Long.valueOf("500"))
      .trafficMegabytes(Long.valueOf("4900"))
      .build();

      // Invoke operation
      Response<Void> response = service.updateQuota(updateQuotaOptions).execute();
      // Validate response
      assertNotNull(response);
      assertEquals(response.getStatusCode(), 200);
    } catch (ServiceResponseException e) {
        fail(String.format("Service returned status code %d: %s\nError details: %s",
          e.getStatusCode(), e.getMessage(), e.getDebuggingInfo()));
    }
  }

  @Test(dependsOnMethods = { "testUpdateQuota" })
  public void testGetQuota() throws Exception {
    try {
      GetQuotaOptions getQuotaOptions = new GetQuotaOptions();

      // Invoke operation
      Response<Quota> response = service.getQuota(getQuotaOptions).execute();
      // Validate response
      assertNotNull(response);
      assertEquals(response.getStatusCode(), 200);

      Quota quotaResult = response.getResult();

      assertEquals(quotaResult.getLimit().getStorageBytes(), Long.valueOf("524288000"));

      assertNotNull(quotaResult);
    } catch (ServiceResponseException e) {
        fail(String.format("Service returned status code %d: %s\nError details: %s",
          e.getStatusCode(), e.getMessage(), e.getDebuggingInfo()));
    }
  }

  @Test(dependsOnMethods = { "testGetQuota" })
  public void testListRetentionPolicies() throws Exception {
    try {
      ListRetentionPoliciesOptions listRetentionPoliciesOptions = new ListRetentionPoliciesOptions();

      // Invoke operation
      Response<Map<String, RetentionPolicy>> response = service.listRetentionPolicies(listRetentionPoliciesOptions).execute();
      // Validate response
      assertNotNull(response);
      assertEquals(response.getStatusCode(), 200);

      Map<String, RetentionPolicy> mapStringRetentionPolicyResult = response.getResult();

      assertNotNull(mapStringRetentionPolicyResult);
    } catch (ServiceResponseException e) {
        fail(String.format("Service returned status code %d: %s\nError details: %s",
          e.getStatusCode(), e.getMessage(), e.getDebuggingInfo()));
    }
  }

  @Test(dependsOnMethods = { "testListRetentionPolicies" })
  public void testSetRetentionPolicy() throws Exception {
    try {
      SetRetentionPolicyOptions setRetentionPolicyOptions = new SetRetentionPolicyOptions.Builder()
      .namespace(namespaceLink)
      .imagesPerRepo(Long.valueOf("10"))
      .retainUntagged(false)
      .build();

      // Invoke operation
      Response<Void> response = service.setRetentionPolicy(setRetentionPolicyOptions).execute();
      // Validate response
      assertNotNull(response);
      assertEquals(response.getStatusCode(), 201);
    } catch (ServiceResponseException e) {
        fail(String.format("Service returned status code %d: %s\nError details: %s",
          e.getStatusCode(), e.getMessage(), e.getDebuggingInfo()));
    }
  }

  @Test(dependsOnMethods = { "testSetRetentionPolicy" })
  public void testAnalyzeRetentionPolicy() throws Exception {
    try {
      AnalyzeRetentionPolicyOptions analyzeRetentionPolicyOptions = new AnalyzeRetentionPolicyOptions.Builder()
      .namespace(namespaceLink)
      .imagesPerRepo(Long.valueOf("10"))
      .retainUntagged(false)
      .build();

      // Invoke operation
      Response<Map<String, List<String>>> response = service.analyzeRetentionPolicy(analyzeRetentionPolicyOptions).execute();
      // Validate response
      assertNotNull(response);
      assertEquals(response.getStatusCode(), 200);

      Map<String, List<String>> mapStringListStringResult = response.getResult();

      assertNotNull(mapStringListStringResult);
    } catch (ServiceResponseException e) {
        fail(String.format("Service returned status code %d: %s\nError details: %s",
          e.getStatusCode(), e.getMessage(), e.getDebuggingInfo()));
    }
  }

  @Test(dependsOnMethods = { "testAnalyzeRetentionPolicy" })
  public void testGetRetentionPolicy() throws Exception {
    try {
      GetRetentionPolicyOptions getRetentionPolicyOptions = new GetRetentionPolicyOptions.Builder()
      .namespace(namespaceLink)
      .build();

      // Invoke operation
      Response<RetentionPolicy> response = service.getRetentionPolicy(getRetentionPolicyOptions).execute();
      // Validate response
      assertNotNull(response);
      assertEquals(response.getStatusCode(), 200);

      RetentionPolicy retentionPolicyResult = response.getResult();

      assertNotNull(retentionPolicyResult);

      assertEquals(retentionPolicyResult.imagesPerRepo(), Long.valueOf("10"));
    } catch (ServiceResponseException e) {
        fail(String.format("Service returned status code %d: %s\nError details: %s",
          e.getStatusCode(), e.getMessage(), e.getDebuggingInfo()));
    }
  }

  @Test(dependsOnMethods = { "testGetRetentionPolicy" })
  public void testDeleteImageTag() throws Exception {
    try {
      DeleteImageTagOptions deleteImageTagOptions = new DeleteImageTagOptions.Builder()
      .image(dnsName+"/"+namespaceLink+"/sdktest:1")
      .build();

      // Invoke operation
      Response<ImageDeleteResult> response = service.deleteImageTag(deleteImageTagOptions).execute();
      // Validate response
      assertNotNull(response);
      assertEquals(response.getStatusCode(), 200);

      ImageDeleteResult imageDeleteResultResult = response.getResult();

      assertNotNull(imageDeleteResultResult);
    } catch (ServiceResponseException e) {
        fail(String.format("Service returned status code %d: %s\nError details: %s",
          e.getStatusCode(), e.getMessage(), e.getDebuggingInfo()));
    }
  }

  @Test(dependsOnMethods = { "testDeleteImageTag" })
  public void testDeleteImage() throws Exception {
    try {
      DeleteImageOptions deleteImageOptions = new DeleteImageOptions.Builder()
      .image(dnsName+"/"+namespaceLink+"/sdktest@"+seedDigest)
      .build();

      // Invoke operation
      Response<ImageDeleteResult> response = service.deleteImage(deleteImageOptions).execute();
      // Validate response
      assertNotNull(response);
      assertEquals(response.getStatusCode(), 200);

      ImageDeleteResult imageDeleteResultResult = response.getResult();

      assertNotNull(imageDeleteResultResult);
    } catch (ServiceResponseException e) {
        fail(String.format("Service returned status code %d: %s\nError details: %s",
          e.getStatusCode(), e.getMessage(), e.getDebuggingInfo()));
    }
  }

  @Test(dependsOnMethods = { "testDeleteImage" })
  public void testListDeletedImages() throws Exception {
    try {
      ListDeletedImagesOptions listDeletedImagesOptions = new ListDeletedImagesOptions.Builder()
      .namespace(namespaceLink)
      .build();

      // Invoke operation
      Response<Map<String, Trash>> response = service.listDeletedImages(listDeletedImagesOptions).execute();
      // Validate response
      assertNotNull(response);
      assertEquals(response.getStatusCode(), 200);

      Map<String, Trash> mapStringTrashResult = response.getResult();

      assertNotNull(mapStringTrashResult);
      assertTrue(mapStringTrashResult.containsKey(dnsName+"/"+namespaceLink+"/sdktest@"+seedDigest));
    } catch (ServiceResponseException e) {
        fail(String.format("Service returned status code %d: %s\nError details: %s",
          e.getStatusCode(), e.getMessage(), e.getDebuggingInfo()));
    }
  }

  @Test(dependsOnMethods = { "testListDeletedImages" })
  public void testRestoreTags() throws Exception {
    try {
      RestoreTagsOptions restoreTagsOptions = new RestoreTagsOptions.Builder()
      .digest(dnsName+"/"+namespaceLink+"/sdktest@"+seedDigest)
      .build();

      // Invoke operation
      Response<RestoreResult> response = service.restoreTags(restoreTagsOptions).execute();
      // Validate response
      assertNotNull(response);
      assertEquals(response.getStatusCode(), 200);

      RestoreResult restoreResultResult = response.getResult();

      assertNotNull(restoreResultResult);
    } catch (ServiceResponseException e) {
        fail(String.format("Service returned status code %d: %s\nError details: %s",
          e.getStatusCode(), e.getMessage(), e.getDebuggingInfo()));
    }
  }

  @Test(dependsOnMethods = { "testRestoreTags" })
  public void testRestoreImage() throws Exception {
    try {
      RestoreImageOptions restoreImageOptions = new RestoreImageOptions.Builder()
      .image(dnsName+"/"+namespaceLink+"/sdktest:nope")
      .build();

      // Invoke operation
      Response<Void> response = service.restoreImage(restoreImageOptions).execute();
    } catch (ServiceResponseException e) {
      assertEquals(e.getStatusCode(), 404);
    }
  }

  @Test(dependsOnMethods = { "testRestoreImage" })
  public void testDeleteNamespace() throws Exception {
    try {
      DeleteNamespaceOptions deleteNamespaceOptions = new DeleteNamespaceOptions.Builder()
      .name(namespaceLink)
      .build();

      // Invoke operation
      Response<Void> response = service.deleteNamespace(deleteNamespaceOptions).execute();
      // Validate response
      assertNotNull(response);
      assertEquals(response.getStatusCode(), 204);
    } catch (ServiceResponseException e) {
        fail(String.format("Service returned status code %d: %s\nError details: %s",
          e.getStatusCode(), e.getMessage(), e.getDebuggingInfo()));
    }
  }

  @AfterClass
  public void tearDown() {
    try {
      // Ensure that a failed test run cannot leave the account in a bad state
      DeleteNamespaceOptions deleteNamespaceOptions = new DeleteNamespaceOptions.Builder()
      .name(namespaceLink)
      .build();

      // Invoke operation
      Response<Void> response = service.deleteNamespace(deleteNamespaceOptions).execute();

    } catch (ServiceResponseException e) {
      // Most likely already deleted, so ignore
    }
    System.out.println("Clean up complete.");
  }
 }
