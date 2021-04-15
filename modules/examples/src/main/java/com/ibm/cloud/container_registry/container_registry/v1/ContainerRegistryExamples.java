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
import com.ibm.cloud.container_registry.container_registry.v1.model.RemoteAPIImage;
import com.ibm.cloud.container_registry.container_registry.v1.model.RestoreImageOptions;
import com.ibm.cloud.container_registry.container_registry.v1.model.RestoreResult;
import com.ibm.cloud.container_registry.container_registry.v1.model.RestoreTagsOptions;
import com.ibm.cloud.container_registry.container_registry.v1.model.RetentionPolicy;
import com.ibm.cloud.container_registry.container_registry.v1.model.SetRetentionPolicyOptions;
import com.ibm.cloud.container_registry.container_registry.v1.model.TagImageOptions;
import com.ibm.cloud.container_registry.container_registry.v1.model.Trash;
import com.ibm.cloud.container_registry.container_registry.v1.model.UpdateAuthOptions;
import com.ibm.cloud.container_registry.container_registry.v1.model.UpdatePlansOptions;
import com.ibm.cloud.container_registry.container_registry.v1.model.UpdateQuotaOptions;
import com.ibm.cloud.container_registry.container_registry.v1.model.UpdateSettingsOptions;
import com.ibm.cloud.sdk.core.http.Response;
import com.ibm.cloud.sdk.core.service.exception.ServiceResponseException;
import com.ibm.cloud.sdk.core.util.CredentialUtils;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//
// This file provides an example of how to use the Container Registry service.
//
// The following configuration properties are assumed to be defined:
// CONTAINER_REGISTRY_URL=<service base url>
// CONTAINER_REGISTRY_AUTH_TYPE=iam
// CONTAINER_REGISTRY_APIKEY=<IAM apikey>
// CONTAINER_REGISTRY_AUTH_URL=<IAM token service base URL - omit this if using the production environment>
//
// These configuration properties can be exported as environment variables, or stored
// in a configuration file and then:
// export IBM_CREDENTIALS_FILE=<name of configuration file>
//
public class ContainerRegistryExamples {
  private static final Logger logger = LoggerFactory.getLogger(ContainerRegistryExamples.class);
  protected ContainerRegistryExamples() { }

  @SuppressWarnings("checkstyle:methodlength")
  public static void main(String[] args) throws Exception {
    String account = "accountID";

    ContainerRegistry service = ContainerRegistry.newInstance(account);

    // Load up our test-specific config properties.
    Map<String, String> config = CredentialUtils.getServiceProperties(ContainerRegistry.DEFAULT_SERVICE_NAME);

    // Variables to hold link values
    String namespaceLink = null;

    try {
      // begin-create_namespace
      CreateNamespaceOptions createNamespaceOptions = new CreateNamespaceOptions.Builder()
        .name("my_example_namespace")
        .build();

      Response<Namespace> response = service.createNamespace(createNamespaceOptions).execute();
      Namespace namespace = response.getResult();

      System.out.println(namespace);
      // end-create_namespace

      namespaceLink = namespace.getNamespace();
    } catch (ServiceResponseException e) {
        logger.error(String.format("Service returned status code %s: %s\nError details: %s",
          e.getStatusCode(), e.getMessage(), e.getDebuggingInfo()), e);
    }

    try {
      // begin-get_auth
      GetAuthOptions getAuthOptions = new GetAuthOptions();

      Response<AuthOptions> response = service.getAuth(getAuthOptions).execute();
      AuthOptions authOptions = response.getResult();

      System.out.println(authOptions);
      // end-get_auth
    } catch (ServiceResponseException e) {
        logger.error(String.format("Service returned status code %s: %s\nError details: %s",
          e.getStatusCode(), e.getMessage(), e.getDebuggingInfo()), e);
    }

    try {
      // begin-update_auth
      UpdateAuthOptions updateAuthOptions = new UpdateAuthOptions.Builder()
        .iamAuthz(true)
        .build();

      service.updateAuth(updateAuthOptions).execute();
      // end-update_auth
    } catch (ServiceResponseException e) {
        logger.error(String.format("Service returned status code %s: %s\nError details: %s",
          e.getStatusCode(), e.getMessage(), e.getDebuggingInfo()), e);
    }

    try {
      // begin-list_images
      ListImagesOptions listImagesOptions = new ListImagesOptions.Builder()
        .build();

      Response<List<RemoteAPIImage>> response = service.listImages(listImagesOptions).execute();
      List<RemoteAPIImage> listRemoteApiImage = response.getResult();

      System.out.println(listRemoteApiImage);
      // end-list_images
    } catch (ServiceResponseException e) {
        logger.error(String.format("Service returned status code %s: %s\nError details: %s",
          e.getStatusCode(), e.getMessage(), e.getDebuggingInfo()), e);
    }

    try {
      // begin-bulk_delete_images
      BulkDeleteImagesOptions bulkDeleteImagesOptions = new BulkDeleteImagesOptions.Builder()
        .bulkDelete(new java.util.ArrayList<String>(java.util.Arrays.asList("image name")))
        .build();

      Response<ImageBulkDeleteResult> response = service.bulkDeleteImages(bulkDeleteImagesOptions).execute();
      ImageBulkDeleteResult imageBulkDeleteResult = response.getResult();

      System.out.println(imageBulkDeleteResult);
      // end-bulk_delete_images
    } catch (ServiceResponseException e) {
        logger.error(String.format("Service returned status code %s: %s\nError details: %s",
          e.getStatusCode(), e.getMessage(), e.getDebuggingInfo()), e);
    }

    try {
      // begin-list_image_digests
      ListImageDigestsOptions listImageDigestsOptions = new ListImageDigestsOptions.Builder()
        .excludeTagged(false)
        .excludeVa(false)
        .includeIbm(false)
        .build();

      Response<List<ImageDigest>> response = service.listImageDigests(listImageDigestsOptions).execute();
      List<ImageDigest> listImageDigest = response.getResult();

      System.out.println(listImageDigest);
      // end-list_image_digests
    } catch (ServiceResponseException e) {
        logger.error(String.format("Service returned status code %s: %s\nError details: %s",
          e.getStatusCode(), e.getMessage(), e.getDebuggingInfo()), e);
    }

    try {
      // begin-tag_image
      TagImageOptions tagImageOptions = new TagImageOptions.Builder()
        .fromimage("from image name")
        .toimage("to image name")
        .build();

      service.tagImage(tagImageOptions).execute();
      // end-tag_image
    } catch (ServiceResponseException e) {
        logger.error(String.format("Service returned status code %s: %s\nError details: %s",
          e.getStatusCode(), e.getMessage(), e.getDebuggingInfo()), e);
    }

    try {
      // begin-inspect_image
      InspectImageOptions inspectImageOptions = new InspectImageOptions.Builder()
        .image("image name")
        .build();

      Response<ImageInspection> response = service.inspectImage(inspectImageOptions).execute();
      ImageInspection imageInspection = response.getResult();

      System.out.println(imageInspection);
      // end-inspect_image
    } catch (ServiceResponseException e) {
        logger.error(String.format("Service returned status code %s: %s\nError details: %s",
          e.getStatusCode(), e.getMessage(), e.getDebuggingInfo()), e);
    }

    try {
      // begin-get_image_manifest
      GetImageManifestOptions getImageManifestOptions = new GetImageManifestOptions.Builder()
        .image("image name")
        .build();

      Response<Map<String, Object>> response = service.getImageManifest(getImageManifestOptions).execute();
      Map<String, Object> imageManifest = response.getResult();

      System.out.println(imageManifest);
      // end-get_image_manifest
    } catch (ServiceResponseException e) {
        logger.error(String.format("Service returned status code %s: %s\nError details: %s",
          e.getStatusCode(), e.getMessage(), e.getDebuggingInfo()), e);
    }

    try {
      // begin-get_messages
      GetMessagesOptions getMessagesOptions = new GetMessagesOptions();

      Response<String> response = service.getMessages(getMessagesOptions).execute();
      String getMessagesResponse = response.getResult();

      System.out.println(getMessagesResponse);
      // end-get_messages
    } catch (ServiceResponseException e) {
        logger.error(String.format("Service returned status code %s: %s\nError details: %s",
          e.getStatusCode(), e.getMessage(), e.getDebuggingInfo()), e);
    }

    try {
      // begin-list_namespaces
      ListNamespacesOptions listNamespacesOptions = new ListNamespacesOptions();

      Response<List<String>> response = service.listNamespaces(listNamespacesOptions).execute();
      List<String> result = response.getResult();

      System.out.println(result);
      // end-list_namespaces
    } catch (ServiceResponseException e) {
        logger.error(String.format("Service returned status code %s: %s\nError details: %s",
          e.getStatusCode(), e.getMessage(), e.getDebuggingInfo()), e);
    }

    try {
      // begin-list_namespace_details
      ListNamespaceDetailsOptions listNamespaceDetailsOptions = new ListNamespaceDetailsOptions();

      Response<List<NamespaceDetails>> response = service.listNamespaceDetails(listNamespaceDetailsOptions).execute();
      List<NamespaceDetails> listNamespaceDetails = response.getResult();

      System.out.println(listNamespaceDetails);
      // end-list_namespace_details
    } catch (ServiceResponseException e) {
        logger.error(String.format("Service returned status code %s: %s\nError details: %s",
          e.getStatusCode(), e.getMessage(), e.getDebuggingInfo()), e);
    }

    try {
      // begin-assign_namespace
      AssignNamespaceOptions assignNamespaceOptions = new AssignNamespaceOptions.Builder()
        .xAuthResourceGroup("Resource Group ID")
        .name(namespaceLink)
        .build();

      Response<Namespace> response = service.assignNamespace(assignNamespaceOptions).execute();
      Namespace namespace = response.getResult();

      System.out.println(namespace);
      // end-assign_namespace
    } catch (ServiceResponseException e) {
        logger.error(String.format("Service returned status code %s: %s\nError details: %s",
          e.getStatusCode(), e.getMessage(), e.getDebuggingInfo()), e);
    }

    try {
      // begin-get_plans
      GetPlansOptions getPlansOptions = new GetPlansOptions();

      Response<Plan> response = service.getPlans(getPlansOptions).execute();
      Plan plan = response.getResult();

      System.out.println(plan);
      // end-get_plans
    } catch (ServiceResponseException e) {
        logger.error(String.format("Service returned status code %s: %s\nError details: %s",
          e.getStatusCode(), e.getMessage(), e.getDebuggingInfo()), e);
    }

    try {
      // begin-update_plans
      UpdatePlansOptions updatePlansOptions = new UpdatePlansOptions.Builder()
        .plan("Standard")
        .build();

      service.updatePlans(updatePlansOptions).execute();
      // end-update_plans
    } catch (ServiceResponseException e) {
        logger.error(String.format("Service returned status code %s: %s\nError details: %s",
          e.getStatusCode(), e.getMessage(), e.getDebuggingInfo()), e);
    }

    try {
      // begin-get_quota
      GetQuotaOptions getQuotaOptions = new GetQuotaOptions();

      Response<Quota> response = service.getQuota(getQuotaOptions).execute();
      Quota quota = response.getResult();

      System.out.println(quota);
      // end-get_quota
    } catch (ServiceResponseException e) {
        logger.error(String.format("Service returned status code %s: %s\nError details: %s",
          e.getStatusCode(), e.getMessage(), e.getDebuggingInfo()), e);
    }

    try {
      // begin-update_quota
      UpdateQuotaOptions updateQuotaOptions = new UpdateQuotaOptions.Builder()
        .trafficMegabytes(Long.valueOf("480"))
        .build();

      service.updateQuota(updateQuotaOptions).execute();
      // end-update_quota
    } catch (ServiceResponseException e) {
        logger.error(String.format("Service returned status code %s: %s\nError details: %s",
          e.getStatusCode(), e.getMessage(), e.getDebuggingInfo()), e);
    }

    try {
      // begin-list_retention_policies
      ListRetentionPoliciesOptions listRetentionPoliciesOptions = new ListRetentionPoliciesOptions();

      Response<Map<String, RetentionPolicy>> response = service.listRetentionPolicies(listRetentionPoliciesOptions).execute();
      Map<String, RetentionPolicy> mapStringRetentionPolicy = response.getResult();

      System.out.println(mapStringRetentionPolicy);
      // end-list_retention_policies
    } catch (ServiceResponseException e) {
        logger.error(String.format("Service returned status code %s: %s\nError details: %s",
          e.getStatusCode(), e.getMessage(), e.getDebuggingInfo()), e);
    }

    try {
      // begin-set_retention_policy
      SetRetentionPolicyOptions setRetentionPolicyOptions = new SetRetentionPolicyOptions.Builder()
        .namespace(namespaceLink)
        .imagesPerRepo(Long.valueOf("10"))
        .retainUntagged(false)
        .build();

      service.setRetentionPolicy(setRetentionPolicyOptions).execute();
      // end-set_retention_policy
    } catch (ServiceResponseException e) {
        logger.error(String.format("Service returned status code %s: %s\nError details: %s",
          e.getStatusCode(), e.getMessage(), e.getDebuggingInfo()), e);
    }

    try {
      // begin-analyze_retention_policy
      AnalyzeRetentionPolicyOptions analyzeRetentionPolicyOptions = new AnalyzeRetentionPolicyOptions.Builder()
        .namespace(namespaceLink)
        .imagesPerRepo(Long.valueOf("10"))
        .retainUntagged(false)
        .build();

      Response<Map<String, List<String>>> response = service.analyzeRetentionPolicy(analyzeRetentionPolicyOptions).execute();
      Map<String, List<String>> mapStringListString = response.getResult();

      System.out.println(mapStringListString);
      // end-analyze_retention_policy
    } catch (ServiceResponseException e) {
        logger.error(String.format("Service returned status code %s: %s\nError details: %s",
          e.getStatusCode(), e.getMessage(), e.getDebuggingInfo()), e);
    }

    try {
      // begin-get_retention_policy
      GetRetentionPolicyOptions getRetentionPolicyOptions = new GetRetentionPolicyOptions.Builder()
        .namespace("testString")
        .build();

      Response<RetentionPolicy> response = service.getRetentionPolicy(getRetentionPolicyOptions).execute();
      RetentionPolicy retentionPolicy = response.getResult();

      System.out.println(retentionPolicy);
      // end-get_retention_policy
    } catch (ServiceResponseException e) {
        logger.error(String.format("Service returned status code %s: %s\nError details: %s",
          e.getStatusCode(), e.getMessage(), e.getDebuggingInfo()), e);
    }

    try {
      // begin-get_settings
      GetSettingsOptions getSettingsOptions = new GetSettingsOptions();

      Response<AccountSettings> response = service.getSettings(getSettingsOptions).execute();
      AccountSettings accountSettings = response.getResult();

      System.out.println(accountSettings);
      // end-get_settings
    } catch (ServiceResponseException e) {
        logger.error(String.format("Service returned status code %s: %s\nError details: %s",
          e.getStatusCode(), e.getMessage(), e.getDebuggingInfo()), e);
    }

    try {
      // begin-update_settings
      UpdateSettingsOptions updateSettingsOptions = new UpdateSettingsOptions.Builder()
        .platformMetrics(true)
        .build();

      service.updateSettings(updateSettingsOptions).execute();
      // end-update_settings
    } catch (ServiceResponseException e) {
        logger.error(String.format("Service returned status code %s: %s\nError details: %s",
          e.getStatusCode(), e.getMessage(), e.getDebuggingInfo()), e);
    }

    try {
      // begin-list_deleted_images
      ListDeletedImagesOptions listDeletedImagesOptions = new ListDeletedImagesOptions.Builder()
        .build();

      Response<Map<String, Trash>> response = service.listDeletedImages(listDeletedImagesOptions).execute();
      Map<String, Trash> mapStringTrash = response.getResult();

      System.out.println(mapStringTrash);
      // end-list_deleted_images
    } catch (ServiceResponseException e) {
        logger.error(String.format("Service returned status code %s: %s\nError details: %s",
          e.getStatusCode(), e.getMessage(), e.getDebuggingInfo()), e);
    }

    try {
      // begin-restore_tags
      RestoreTagsOptions restoreTagsOptions = new RestoreTagsOptions.Builder()
        .digest("image name") // Fully qualified including digest
        .build();

      Response<RestoreResult> response = service.restoreTags(restoreTagsOptions).execute();
      RestoreResult restoreResult = response.getResult();

      System.out.println(restoreResult);
      // end-restore_tags
    } catch (ServiceResponseException e) {
        logger.error(String.format("Service returned status code %s: %s\nError details: %s",
          e.getStatusCode(), e.getMessage(), e.getDebuggingInfo()), e);
    }

    try {
      // begin-restore_image
      RestoreImageOptions restoreImageOptions = new RestoreImageOptions.Builder()
        .image("image name")
        .build();

      service.restoreImage(restoreImageOptions).execute();
      // end-restore_image
    } catch (ServiceResponseException e) {
        logger.error(String.format("Service returned status code %s: %s\nError details: %s",
          e.getStatusCode(), e.getMessage(), e.getDebuggingInfo()), e);
    }

    try {
      // begin-delete_namespace
      DeleteNamespaceOptions deleteNamespaceOptions = new DeleteNamespaceOptions.Builder()
        .name(namespaceLink)
        .build();

      service.deleteNamespace(deleteNamespaceOptions).execute();
      // end-delete_namespace
    } catch (ServiceResponseException e) {
        logger.error(String.format("Service returned status code %s: %s\nError details: %s",
          e.getStatusCode(), e.getMessage(), e.getDebuggingInfo()), e);
    }

    try {
      // begin-delete_image_tag
      DeleteImageTagOptions deleteImageTagOptions = new DeleteImageTagOptions.Builder()
        .image("image name")
        .build();

      Response<ImageDeleteResult> response = service.deleteImageTag(deleteImageTagOptions).execute();
      ImageDeleteResult imageDeleteResult = response.getResult();

      System.out.println(imageDeleteResult);
      // end-delete_image_tag
    } catch (ServiceResponseException e) {
        logger.error(String.format("Service returned status code %s: %s\nError details: %s",
          e.getStatusCode(), e.getMessage(), e.getDebuggingInfo()), e);
    }

    try {
      // begin-delete_image
      DeleteImageOptions deleteImageOptions = new DeleteImageOptions.Builder()
        .image("image name")
        .build();

      Response<ImageDeleteResult> response = service.deleteImage(deleteImageOptions).execute();
      ImageDeleteResult imageDeleteResult = response.getResult();

      System.out.println(imageDeleteResult);
      // end-delete_image
    } catch (ServiceResponseException e) {
        logger.error(String.format("Service returned status code %s: %s\nError details: %s",
          e.getStatusCode(), e.getMessage(), e.getDebuggingInfo()), e);
    }

  }
}
