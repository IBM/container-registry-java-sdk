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

/*
 * IBM OpenAPI SDK Code Generator Version: 3.29.1-b338fb38-20210313-010605
 */

package com.ibm.cloud.container_registry.container_registry.v1;

import com.google.gson.JsonObject;
import com.ibm.cloud.container_registry.common.SdkCommon;
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
import com.ibm.cloud.sdk.core.http.RequestBuilder;
import com.ibm.cloud.sdk.core.http.ResponseConverter;
import com.ibm.cloud.sdk.core.http.ServiceCall;
import com.ibm.cloud.sdk.core.security.Authenticator;
import com.ibm.cloud.sdk.core.security.ConfigBasedAuthenticatorFactory;
import com.ibm.cloud.sdk.core.service.BaseService;
import com.ibm.cloud.sdk.core.util.ResponseConverterUtils;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Management interface for IBM Cloud Container Registry.
 *
 * @version v1
 */
public class ContainerRegistry extends BaseService {

  public static final String DEFAULT_SERVICE_NAME = "container_registry";

  public static final String DEFAULT_SERVICE_URL = "https://us.icr.io";

  private String account;

 /**
   * Class method which constructs an instance of the `ContainerRegistry` client.
   * The default service name is used to configure the client instance.
   *
   * @param account The unique ID for your IBM Cloud account.
   * @return an instance of the `ContainerRegistry` client using external configuration
   */
  public static ContainerRegistry newInstance(String account) {
    return newInstance(account, DEFAULT_SERVICE_NAME);
  }

  /**
   * Class method which constructs an instance of the `ContainerRegistry` client.
   * The specified service name is used to configure the client instance.
   *
   * @param account The unique ID for your IBM Cloud account.
   * @param serviceName the service name to be used when configuring the client instance
   * @return an instance of the `ContainerRegistry` client using external configuration
   */
  public static ContainerRegistry newInstance(String account, String serviceName) {
    Authenticator authenticator = ConfigBasedAuthenticatorFactory.getAuthenticator(serviceName);
    ContainerRegistry service = new ContainerRegistry(account, serviceName, authenticator);
    service.configureService(serviceName);
    return service;
  }

  /**
   * Constructs an instance of the `ContainerRegistry` client.
   * The specified service name and authenticator are used to configure the client instance.
   *
   * @param account The unique ID for your IBM Cloud account.
   * @param serviceName the service name to be used when configuring the client instance
   * @param authenticator the {@link Authenticator} instance to be configured for this client
   */
  public ContainerRegistry(String account, String serviceName, Authenticator authenticator) {
    super(serviceName, authenticator);
    setServiceUrl(DEFAULT_SERVICE_URL);
    setAccount(account);
  }

  /**
   * Gets the account.
   *
   * The unique ID for your IBM Cloud account.
   *
   * @return the account
   */
  public String getAccount() {
    return this.account;
  }

  /**
   * Sets the account.
   *
   * @param account the new account
   */
  public void setAccount(final String account) {
    com.ibm.cloud.sdk.core.util.Validator.notEmpty(account, "account cannot be empty.");
    this.account = account;
  }

  /**
   * Get authorization options.
   *
   * Get authorization options for the targeted account.
   *
   * @param getAuthOptions the {@link GetAuthOptions} containing the options for the call
   * @return a {@link ServiceCall} with a result of type {@link AuthOptions}
   */
  public ServiceCall<AuthOptions> getAuth(GetAuthOptions getAuthOptions) {
    RequestBuilder builder = RequestBuilder.get(RequestBuilder.resolveRequestUrl(getServiceUrl(), "/api/v1/auth"));
    Map<String, String> sdkHeaders = SdkCommon.getSdkHeaders("container_registry", "v1", "getAuth");
    for (Entry<String, String> header : sdkHeaders.entrySet()) {
      builder.header(header.getKey(), header.getValue());
    }
    builder.header("Accept", "application/json");
    builder.header("Account", this.account);
    ResponseConverter<AuthOptions> responseConverter =
      ResponseConverterUtils.getValue(new com.google.gson.reflect.TypeToken<AuthOptions>() { }.getType());
    return createServiceCall(builder.build(), responseConverter);
  }

  /**
   * Get authorization options.
   *
   * Get authorization options for the targeted account.
   *
   * @return a {@link ServiceCall} with a result of type {@link AuthOptions}
   */
  public ServiceCall<AuthOptions> getAuth() {
    return getAuth(null);
  }

  /**
   * Update authorization options.
   *
   * Update authorization options for the targeted account.
   *
   * @param updateAuthOptions the {@link UpdateAuthOptions} containing the options for the call
   * @return a {@link ServiceCall} with a void result
   */
  public ServiceCall<Void> updateAuth(UpdateAuthOptions updateAuthOptions) {
    com.ibm.cloud.sdk.core.util.Validator.notNull(updateAuthOptions,
      "updateAuthOptions cannot be null");
    RequestBuilder builder = RequestBuilder.patch(RequestBuilder.resolveRequestUrl(getServiceUrl(), "/api/v1/auth"));
    Map<String, String> sdkHeaders = SdkCommon.getSdkHeaders("container_registry", "v1", "updateAuth");
    for (Entry<String, String> header : sdkHeaders.entrySet()) {
      builder.header(header.getKey(), header.getValue());
    }
    builder.header("Account", this.account);
    final JsonObject contentJson = new JsonObject();
    if (updateAuthOptions.iamAuthz() != null) {
      contentJson.addProperty("iam_authz", updateAuthOptions.iamAuthz());
    }
    if (updateAuthOptions.privateOnly() != null) {
      contentJson.addProperty("private_only", updateAuthOptions.privateOnly());
    }
    builder.bodyJson(contentJson);
    ResponseConverter<Void> responseConverter = ResponseConverterUtils.getVoid();
    return createServiceCall(builder.build(), responseConverter);
  }

  /**
   * Update authorization options.
   *
   * Update authorization options for the targeted account.
   *
   * @return a {@link ServiceCall} with a void result
   */
  public ServiceCall<Void> updateAuth() {
    return updateAuth(null);
  }

  /**
   * List images.
   *
   * List all images in namespaces in a targeted IBM Cloud account.
   *
   * @param listImagesOptions the {@link ListImagesOptions} containing the options for the call
   * @return a {@link ServiceCall} with a result of type {@link List}
   */
  public ServiceCall<List<RemoteAPIImage>> listImages(ListImagesOptions listImagesOptions) {
    if (listImagesOptions == null) {
      listImagesOptions = new ListImagesOptions.Builder().build();
    }
    RequestBuilder builder = RequestBuilder.get(RequestBuilder.resolveRequestUrl(getServiceUrl(), "/api/v1/images"));
    Map<String, String> sdkHeaders = SdkCommon.getSdkHeaders("container_registry", "v1", "listImages");
    for (Entry<String, String> header : sdkHeaders.entrySet()) {
      builder.header(header.getKey(), header.getValue());
    }
    builder.header("Accept", "application/json");
    builder.header("Account", this.account);
    if (listImagesOptions.namespace() != null) {
      builder.query("namespace", String.valueOf(listImagesOptions.namespace()));
    }
    if (listImagesOptions.includeIbm() != null) {
      builder.query("includeIBM", String.valueOf(listImagesOptions.includeIbm()));
    }
    if (listImagesOptions.includePrivate() != null) {
      builder.query("includePrivate", String.valueOf(listImagesOptions.includePrivate()));
    }
    if (listImagesOptions.includeManifestLists() != null) {
      builder.query("includeManifestLists", String.valueOf(listImagesOptions.includeManifestLists()));
    }
    if (listImagesOptions.vulnerabilities() != null) {
      builder.query("vulnerabilities", String.valueOf(listImagesOptions.vulnerabilities()));
    }
    if (listImagesOptions.repository() != null) {
      builder.query("repository", String.valueOf(listImagesOptions.repository()));
    }
    ResponseConverter<List<RemoteAPIImage>> responseConverter =
      ResponseConverterUtils.getValue(new com.google.gson.reflect.TypeToken<List<RemoteAPIImage>>() { }.getType());
    return createServiceCall(builder.build(), responseConverter);
  }

  /**
   * List images.
   *
   * List all images in namespaces in a targeted IBM Cloud account.
   *
   * @return a {@link ServiceCall} with a result of type {@link List}
   */
  public ServiceCall<List<RemoteAPIImage>> listImages() {
    return listImages(null);
  }

  /**
   * Bulk delete images.
   *
   * Remove multiple container images from the registry.
   *
   * @param bulkDeleteImagesOptions the {@link BulkDeleteImagesOptions} containing the options for the call
   * @return a {@link ServiceCall} with a result of type {@link ImageBulkDeleteResult}
   */
  public ServiceCall<ImageBulkDeleteResult> bulkDeleteImages(BulkDeleteImagesOptions bulkDeleteImagesOptions) {
    com.ibm.cloud.sdk.core.util.Validator.notNull(bulkDeleteImagesOptions,
      "bulkDeleteImagesOptions cannot be null");
    RequestBuilder builder = RequestBuilder.post(RequestBuilder.resolveRequestUrl(getServiceUrl(), "/api/v1/images/bulkdelete"));
    Map<String, String> sdkHeaders = SdkCommon.getSdkHeaders("container_registry", "v1", "bulkDeleteImages");
    for (Entry<String, String> header : sdkHeaders.entrySet()) {
      builder.header(header.getKey(), header.getValue());
    }
    builder.header("Accept", "application/json");
    builder.header("Account", this.account);
    builder.bodyContent(com.ibm.cloud.sdk.core.util.GsonSingleton.getGsonWithoutPrettyPrinting().toJson(bulkDeleteImagesOptions.bulkDelete()), "application/json");
    ResponseConverter<ImageBulkDeleteResult> responseConverter =
      ResponseConverterUtils.getValue(new com.google.gson.reflect.TypeToken<ImageBulkDeleteResult>() { }.getType());
    return createServiceCall(builder.build(), responseConverter);
  }

  /**
   * List images by digest.
   *
   * List all images by digest in namespaces in a targeted IBM Cloud account.
   *
   * @param listImageDigestsOptions the {@link ListImageDigestsOptions} containing the options for the call
   * @return a {@link ServiceCall} with a result of type {@link List}
   */
  public ServiceCall<List<ImageDigest>> listImageDigests(ListImageDigestsOptions listImageDigestsOptions) {
    com.ibm.cloud.sdk.core.util.Validator.notNull(listImageDigestsOptions,
      "listImageDigestsOptions cannot be null");
    RequestBuilder builder = RequestBuilder.post(RequestBuilder.resolveRequestUrl(getServiceUrl(), "/api/v1/images/digests"));
    Map<String, String> sdkHeaders = SdkCommon.getSdkHeaders("container_registry", "v1", "listImageDigests");
    for (Entry<String, String> header : sdkHeaders.entrySet()) {
      builder.header(header.getKey(), header.getValue());
    }
    builder.header("Accept", "application/json");
    builder.header("Account", this.account);
    final JsonObject contentJson = new JsonObject();
    if (listImageDigestsOptions.excludeTagged() != null) {
      contentJson.addProperty("exclude_tagged", listImageDigestsOptions.excludeTagged());
    }
    if (listImageDigestsOptions.excludeVa() != null) {
      contentJson.addProperty("exclude_va", listImageDigestsOptions.excludeVa());
    }
    if (listImageDigestsOptions.includeIbm() != null) {
      contentJson.addProperty("include_ibm", listImageDigestsOptions.includeIbm());
    }
    if (listImageDigestsOptions.repositories() != null) {
      contentJson.add("repositories", com.ibm.cloud.sdk.core.util.GsonSingleton.getGson().toJsonTree(listImageDigestsOptions.repositories()));
    }
    builder.bodyJson(contentJson);
    ResponseConverter<List<ImageDigest>> responseConverter =
      ResponseConverterUtils.getValue(new com.google.gson.reflect.TypeToken<List<ImageDigest>>() { }.getType());
    return createServiceCall(builder.build(), responseConverter);
  }

  /**
   * List images by digest.
   *
   * List all images by digest in namespaces in a targeted IBM Cloud account.
   *
   * @return a {@link ServiceCall} with a result of type {@link List}
   */
  public ServiceCall<List<ImageDigest>> listImageDigests() {
    return listImageDigests(null);
  }

  /**
   * Create tag.
   *
   * Create a new tag in a private registry that refers to an existing image in the same region.
   *
   * @param tagImageOptions the {@link TagImageOptions} containing the options for the call
   * @return a {@link ServiceCall} with a void result
   */
  public ServiceCall<Void> tagImage(TagImageOptions tagImageOptions) {
    com.ibm.cloud.sdk.core.util.Validator.notNull(tagImageOptions,
      "tagImageOptions cannot be null");
    RequestBuilder builder = RequestBuilder.post(RequestBuilder.resolveRequestUrl(getServiceUrl(), "/api/v1/images/tags"));
    Map<String, String> sdkHeaders = SdkCommon.getSdkHeaders("container_registry", "v1", "tagImage");
    for (Entry<String, String> header : sdkHeaders.entrySet()) {
      builder.header(header.getKey(), header.getValue());
    }
    builder.header("Account", this.account);
    builder.query("fromimage", String.valueOf(tagImageOptions.fromimage()));
    builder.query("toimage", String.valueOf(tagImageOptions.toimage()));
    ResponseConverter<Void> responseConverter = ResponseConverterUtils.getVoid();
    return createServiceCall(builder.build(), responseConverter);
  }

  /**
   * Delete image.
   *
   * Delete a container image from the registry.
   *
   * @param deleteImageOptions the {@link DeleteImageOptions} containing the options for the call
   * @return a {@link ServiceCall} with a result of type {@link ImageDeleteResult}
   */
  public ServiceCall<ImageDeleteResult> deleteImage(DeleteImageOptions deleteImageOptions) {
    com.ibm.cloud.sdk.core.util.Validator.notNull(deleteImageOptions,
      "deleteImageOptions cannot be null");
    Map<String, String> pathParamsMap = new HashMap<String, String>();
    pathParamsMap.put("image", deleteImageOptions.image());
    RequestBuilder builder = RequestBuilder.delete(RequestBuilder.resolveRequestUrl(getServiceUrl(), "/api/v1/images/{image}", pathParamsMap));
    Map<String, String> sdkHeaders = SdkCommon.getSdkHeaders("container_registry", "v1", "deleteImage");
    for (Entry<String, String> header : sdkHeaders.entrySet()) {
      builder.header(header.getKey(), header.getValue());
    }
    builder.header("Accept", "application/json");
    builder.header("Account", this.account);
    ResponseConverter<ImageDeleteResult> responseConverter =
      ResponseConverterUtils.getValue(new com.google.gson.reflect.TypeToken<ImageDeleteResult>() { }.getType());
    return createServiceCall(builder.build(), responseConverter);
  }

  /**
   * Inspect an image.
   *
   * Inspect a container image in the private registry.
   *
   * @param inspectImageOptions the {@link InspectImageOptions} containing the options for the call
   * @return a {@link ServiceCall} with a result of type {@link ImageInspection}
   */
  public ServiceCall<ImageInspection> inspectImage(InspectImageOptions inspectImageOptions) {
    com.ibm.cloud.sdk.core.util.Validator.notNull(inspectImageOptions,
      "inspectImageOptions cannot be null");
    Map<String, String> pathParamsMap = new HashMap<String, String>();
    pathParamsMap.put("image", inspectImageOptions.image());
    RequestBuilder builder = RequestBuilder.get(RequestBuilder.resolveRequestUrl(getServiceUrl(), "/api/v1/images/{image}/json", pathParamsMap));
    Map<String, String> sdkHeaders = SdkCommon.getSdkHeaders("container_registry", "v1", "inspectImage");
    for (Entry<String, String> header : sdkHeaders.entrySet()) {
      builder.header(header.getKey(), header.getValue());
    }
    builder.header("Accept", "application/json");
    builder.header("Account", this.account);
    ResponseConverter<ImageInspection> responseConverter =
      ResponseConverterUtils.getValue(new com.google.gson.reflect.TypeToken<ImageInspection>() { }.getType());
    return createServiceCall(builder.build(), responseConverter);
  }

  /**
   * Get image manifest.
   *
   * Get the manifest for a container image in the private registry.
   *
   * @param getImageManifestOptions the {@link GetImageManifestOptions} containing the options for the call
   * @return a {@link ServiceCall} with a result of type {@link Map}
   */
  public ServiceCall<Map<String, Object>> getImageManifest(GetImageManifestOptions getImageManifestOptions) {
    com.ibm.cloud.sdk.core.util.Validator.notNull(getImageManifestOptions,
      "getImageManifestOptions cannot be null");
    Map<String, String> pathParamsMap = new HashMap<String, String>();
    pathParamsMap.put("image", getImageManifestOptions.image());
    RequestBuilder builder = RequestBuilder.get(RequestBuilder.resolveRequestUrl(getServiceUrl(), "/api/v1/images/{image}/manifest", pathParamsMap));
    Map<String, String> sdkHeaders = SdkCommon.getSdkHeaders("container_registry", "v1", "getImageManifest");
    for (Entry<String, String> header : sdkHeaders.entrySet()) {
      builder.header(header.getKey(), header.getValue());
    }
    builder.header("Accept", "application/json");
    builder.header("Account", this.account);
    ResponseConverter<Map<String, Object>> responseConverter =
      ResponseConverterUtils.getValue(new com.google.gson.reflect.TypeToken<Map<String, Object>>() { }.getType());
    return createServiceCall(builder.build(), responseConverter);
  }

  /**
   * Get messages.
   *
   * Return any published system messages.
   *
   * @param getMessagesOptions the {@link GetMessagesOptions} containing the options for the call
   * @return a {@link ServiceCall} with a result of type {@link String}
   */
  public ServiceCall<String> getMessages(GetMessagesOptions getMessagesOptions) {
    RequestBuilder builder = RequestBuilder.get(RequestBuilder.resolveRequestUrl(getServiceUrl(), "/api/v1/messages"));
    Map<String, String> sdkHeaders = SdkCommon.getSdkHeaders("container_registry", "v1", "getMessages");
    for (Entry<String, String> header : sdkHeaders.entrySet()) {
      builder.header(header.getKey(), header.getValue());
    }
    builder.header("Accept", "application/json");
    ResponseConverter<String> responseConverter = ResponseConverterUtils.getString();
    return createServiceCall(builder.build(), responseConverter);
  }

  /**
   * Get messages.
   *
   * Return any published system messages.
   *
   * @return a {@link ServiceCall} with a result of type {@link String}
   */
  public ServiceCall<String> getMessages() {
    return getMessages(null);
  }

  /**
   * List namespaces.
   *
   * List authorized namespaces in the targeted IBM Cloud account.
   *
   * @param listNamespacesOptions the {@link ListNamespacesOptions} containing the options for the call
   * @return a {@link ServiceCall} with a result of type {@link List}
   */
  public ServiceCall<List<String>> listNamespaces(ListNamespacesOptions listNamespacesOptions) {
    RequestBuilder builder = RequestBuilder.get(RequestBuilder.resolveRequestUrl(getServiceUrl(), "/api/v1/namespaces"));
    Map<String, String> sdkHeaders = SdkCommon.getSdkHeaders("container_registry", "v1", "listNamespaces");
    for (Entry<String, String> header : sdkHeaders.entrySet()) {
      builder.header(header.getKey(), header.getValue());
    }
    builder.header("Accept", "application/json");
    builder.header("Account", this.account);
    ResponseConverter<List<String>> responseConverter =
      ResponseConverterUtils.getValue(new com.google.gson.reflect.TypeToken<List<String>>() { }.getType());
    return createServiceCall(builder.build(), responseConverter);
  }

  /**
   * List namespaces.
   *
   * List authorized namespaces in the targeted IBM Cloud account.
   *
   * @return a {@link ServiceCall} with a result of type {@link List}
   */
  public ServiceCall<List<String>> listNamespaces() {
    return listNamespaces(null);
  }

  /**
   * Detailed namespace list.
   *
   * Retrieves details, such as resource group, for all your namespaces in the targeted registry.
   *
   * @param listNamespaceDetailsOptions the {@link ListNamespaceDetailsOptions} containing the options for the call
   * @return a {@link ServiceCall} with a result of type {@link List}
   */
  public ServiceCall<List<NamespaceDetails>> listNamespaceDetails(ListNamespaceDetailsOptions listNamespaceDetailsOptions) {
    RequestBuilder builder = RequestBuilder.get(RequestBuilder.resolveRequestUrl(getServiceUrl(), "/api/v1/namespaces/details"));
    Map<String, String> sdkHeaders = SdkCommon.getSdkHeaders("container_registry", "v1", "listNamespaceDetails");
    for (Entry<String, String> header : sdkHeaders.entrySet()) {
      builder.header(header.getKey(), header.getValue());
    }
    builder.header("Accept", "application/json");
    builder.header("Account", this.account);
    ResponseConverter<List<NamespaceDetails>> responseConverter =
      ResponseConverterUtils.getValue(new com.google.gson.reflect.TypeToken<List<NamespaceDetails>>() { }.getType());
    return createServiceCall(builder.build(), responseConverter);
  }

  /**
   * Detailed namespace list.
   *
   * Retrieves details, such as resource group, for all your namespaces in the targeted registry.
   *
   * @return a {@link ServiceCall} with a result of type {@link List}
   */
  public ServiceCall<List<NamespaceDetails>> listNamespaceDetails() {
    return listNamespaceDetails(null);
  }

  /**
   * Create namespace.
   *
   * Add a namespace to the targeted IBM Cloud account.
   *
   * @param createNamespaceOptions the {@link CreateNamespaceOptions} containing the options for the call
   * @return a {@link ServiceCall} with a result of type {@link Namespace}
   */
  public ServiceCall<Namespace> createNamespace(CreateNamespaceOptions createNamespaceOptions) {
    com.ibm.cloud.sdk.core.util.Validator.notNull(createNamespaceOptions,
      "createNamespaceOptions cannot be null");
    Map<String, String> pathParamsMap = new HashMap<String, String>();
    pathParamsMap.put("name", createNamespaceOptions.name());
    RequestBuilder builder = RequestBuilder.put(RequestBuilder.resolveRequestUrl(getServiceUrl(), "/api/v1/namespaces/{name}", pathParamsMap));
    Map<String, String> sdkHeaders = SdkCommon.getSdkHeaders("container_registry", "v1", "createNamespace");
    for (Entry<String, String> header : sdkHeaders.entrySet()) {
      builder.header(header.getKey(), header.getValue());
    }
    builder.header("Accept", "application/json");
    builder.header("Account", this.account);
    if (createNamespaceOptions.xAuthResourceGroup() != null) {
      builder.header("X-Auth-Resource-Group", createNamespaceOptions.xAuthResourceGroup());
    }
    ResponseConverter<Namespace> responseConverter =
      ResponseConverterUtils.getValue(new com.google.gson.reflect.TypeToken<Namespace>() { }.getType());
    return createServiceCall(builder.build(), responseConverter);
  }

  /**
   * Assign namespace.
   *
   * Assign a namespace to the specified resource group in the targeted IBM Cloud account.
   *
   * @param assignNamespaceOptions the {@link AssignNamespaceOptions} containing the options for the call
   * @return a {@link ServiceCall} with a result of type {@link Namespace}
   */
  public ServiceCall<Namespace> assignNamespace(AssignNamespaceOptions assignNamespaceOptions) {
    com.ibm.cloud.sdk.core.util.Validator.notNull(assignNamespaceOptions,
      "assignNamespaceOptions cannot be null");
    Map<String, String> pathParamsMap = new HashMap<String, String>();
    pathParamsMap.put("name", assignNamespaceOptions.name());
    RequestBuilder builder = RequestBuilder.patch(RequestBuilder.resolveRequestUrl(getServiceUrl(), "/api/v1/namespaces/{name}", pathParamsMap));
    Map<String, String> sdkHeaders = SdkCommon.getSdkHeaders("container_registry", "v1", "assignNamespace");
    for (Entry<String, String> header : sdkHeaders.entrySet()) {
      builder.header(header.getKey(), header.getValue());
    }
    builder.header("Accept", "application/json");
    builder.header("Account", this.account);
    builder.header("X-Auth-Resource-Group", assignNamespaceOptions.xAuthResourceGroup());
    ResponseConverter<Namespace> responseConverter =
      ResponseConverterUtils.getValue(new com.google.gson.reflect.TypeToken<Namespace>() { }.getType());
    return createServiceCall(builder.build(), responseConverter);
  }

  /**
   * Delete namespace.
   *
   * Delete the IBM Cloud Container Registry namespace from the targeted IBM Cloud account, and removes all images that
   * were in that namespace.
   *
   * @param deleteNamespaceOptions the {@link DeleteNamespaceOptions} containing the options for the call
   * @return a {@link ServiceCall} with a void result
   */
  public ServiceCall<Void> deleteNamespace(DeleteNamespaceOptions deleteNamespaceOptions) {
    com.ibm.cloud.sdk.core.util.Validator.notNull(deleteNamespaceOptions,
      "deleteNamespaceOptions cannot be null");
    Map<String, String> pathParamsMap = new HashMap<String, String>();
    pathParamsMap.put("name", deleteNamespaceOptions.name());
    RequestBuilder builder = RequestBuilder.delete(RequestBuilder.resolveRequestUrl(getServiceUrl(), "/api/v1/namespaces/{name}", pathParamsMap));
    Map<String, String> sdkHeaders = SdkCommon.getSdkHeaders("container_registry", "v1", "deleteNamespace");
    for (Entry<String, String> header : sdkHeaders.entrySet()) {
      builder.header(header.getKey(), header.getValue());
    }
    builder.header("Account", this.account);
    ResponseConverter<Void> responseConverter = ResponseConverterUtils.getVoid();
    return createServiceCall(builder.build(), responseConverter);
  }

  /**
   * Get plans.
   *
   * Get plans for the targeted account.
   *
   * @param getPlansOptions the {@link GetPlansOptions} containing the options for the call
   * @return a {@link ServiceCall} with a result of type {@link Plan}
   */
  public ServiceCall<Plan> getPlans(GetPlansOptions getPlansOptions) {
    RequestBuilder builder = RequestBuilder.get(RequestBuilder.resolveRequestUrl(getServiceUrl(), "/api/v1/plans"));
    Map<String, String> sdkHeaders = SdkCommon.getSdkHeaders("container_registry", "v1", "getPlans");
    for (Entry<String, String> header : sdkHeaders.entrySet()) {
      builder.header(header.getKey(), header.getValue());
    }
    builder.header("Accept", "application/json");
    builder.header("Account", this.account);
    ResponseConverter<Plan> responseConverter =
      ResponseConverterUtils.getValue(new com.google.gson.reflect.TypeToken<Plan>() { }.getType());
    return createServiceCall(builder.build(), responseConverter);
  }

  /**
   * Get plans.
   *
   * Get plans for the targeted account.
   *
   * @return a {@link ServiceCall} with a result of type {@link Plan}
   */
  public ServiceCall<Plan> getPlans() {
    return getPlans(null);
  }

  /**
   * Update plans.
   *
   * Update plans for the targeted account.
   *
   * @param updatePlansOptions the {@link UpdatePlansOptions} containing the options for the call
   * @return a {@link ServiceCall} with a void result
   */
  public ServiceCall<Void> updatePlans(UpdatePlansOptions updatePlansOptions) {
    com.ibm.cloud.sdk.core.util.Validator.notNull(updatePlansOptions,
      "updatePlansOptions cannot be null");
    RequestBuilder builder = RequestBuilder.patch(RequestBuilder.resolveRequestUrl(getServiceUrl(), "/api/v1/plans"));
    Map<String, String> sdkHeaders = SdkCommon.getSdkHeaders("container_registry", "v1", "updatePlans");
    for (Entry<String, String> header : sdkHeaders.entrySet()) {
      builder.header(header.getKey(), header.getValue());
    }
    builder.header("Account", this.account);
    final JsonObject contentJson = new JsonObject();
    if (updatePlansOptions.plan() != null) {
      contentJson.addProperty("plan", updatePlansOptions.plan());
    }
    builder.bodyJson(contentJson);
    ResponseConverter<Void> responseConverter = ResponseConverterUtils.getVoid();
    return createServiceCall(builder.build(), responseConverter);
  }

  /**
   * Update plans.
   *
   * Update plans for the targeted account.
   *
   * @return a {@link ServiceCall} with a void result
   */
  public ServiceCall<Void> updatePlans() {
    return updatePlans(null);
  }

  /**
   * Get quotas.
   *
   * Get quotas for the targeted account.
   *
   * @param getQuotaOptions the {@link GetQuotaOptions} containing the options for the call
   * @return a {@link ServiceCall} with a result of type {@link Quota}
   */
  public ServiceCall<Quota> getQuota(GetQuotaOptions getQuotaOptions) {
    RequestBuilder builder = RequestBuilder.get(RequestBuilder.resolveRequestUrl(getServiceUrl(), "/api/v1/quotas"));
    Map<String, String> sdkHeaders = SdkCommon.getSdkHeaders("container_registry", "v1", "getQuota");
    for (Entry<String, String> header : sdkHeaders.entrySet()) {
      builder.header(header.getKey(), header.getValue());
    }
    builder.header("Accept", "application/json");
    builder.header("Account", this.account);
    ResponseConverter<Quota> responseConverter =
      ResponseConverterUtils.getValue(new com.google.gson.reflect.TypeToken<Quota>() { }.getType());
    return createServiceCall(builder.build(), responseConverter);
  }

  /**
   * Get quotas.
   *
   * Get quotas for the targeted account.
   *
   * @return a {@link ServiceCall} with a result of type {@link Quota}
   */
  public ServiceCall<Quota> getQuota() {
    return getQuota(null);
  }

  /**
   * Update quotas.
   *
   * Update quotas for the targeted account.
   *
   * @param updateQuotaOptions the {@link UpdateQuotaOptions} containing the options for the call
   * @return a {@link ServiceCall} with a void result
   */
  public ServiceCall<Void> updateQuota(UpdateQuotaOptions updateQuotaOptions) {
    com.ibm.cloud.sdk.core.util.Validator.notNull(updateQuotaOptions,
      "updateQuotaOptions cannot be null");
    RequestBuilder builder = RequestBuilder.patch(RequestBuilder.resolveRequestUrl(getServiceUrl(), "/api/v1/quotas"));
    Map<String, String> sdkHeaders = SdkCommon.getSdkHeaders("container_registry", "v1", "updateQuota");
    for (Entry<String, String> header : sdkHeaders.entrySet()) {
      builder.header(header.getKey(), header.getValue());
    }
    builder.header("Account", this.account);
    final JsonObject contentJson = new JsonObject();
    if (updateQuotaOptions.storageMegabytes() != null) {
      contentJson.addProperty("storage_megabytes", updateQuotaOptions.storageMegabytes());
    }
    if (updateQuotaOptions.trafficMegabytes() != null) {
      contentJson.addProperty("traffic_megabytes", updateQuotaOptions.trafficMegabytes());
    }
    builder.bodyJson(contentJson);
    ResponseConverter<Void> responseConverter = ResponseConverterUtils.getVoid();
    return createServiceCall(builder.build(), responseConverter);
  }

  /**
   * Update quotas.
   *
   * Update quotas for the targeted account.
   *
   * @return a {@link ServiceCall} with a void result
   */
  public ServiceCall<Void> updateQuota() {
    return updateQuota(null);
  }

  /**
   * List retention policies.
   *
   * List retention policies for all namespaces in the targeted IBM Cloud account.
   *
   * @param listRetentionPoliciesOptions the {@link ListRetentionPoliciesOptions} containing the options for the call
   * @return a {@link ServiceCall} with a result of type {@link Map}
   */
  public ServiceCall<Map<String, RetentionPolicy>> listRetentionPolicies(ListRetentionPoliciesOptions listRetentionPoliciesOptions) {
    RequestBuilder builder = RequestBuilder.get(RequestBuilder.resolveRequestUrl(getServiceUrl(), "/api/v1/retentions"));
    Map<String, String> sdkHeaders = SdkCommon.getSdkHeaders("container_registry", "v1", "listRetentionPolicies");
    for (Entry<String, String> header : sdkHeaders.entrySet()) {
      builder.header(header.getKey(), header.getValue());
    }
    builder.header("Accept", "application/json");
    builder.header("Account", this.account);
    ResponseConverter<Map<String, RetentionPolicy>> responseConverter =
      ResponseConverterUtils.getValue(new com.google.gson.reflect.TypeToken<Map<String, RetentionPolicy>>() { }.getType());
    return createServiceCall(builder.build(), responseConverter);
  }

  /**
   * List retention policies.
   *
   * List retention policies for all namespaces in the targeted IBM Cloud account.
   *
   * @return a {@link ServiceCall} with a result of type {@link Map}
   */
  public ServiceCall<Map<String, RetentionPolicy>> listRetentionPolicies() {
    return listRetentionPolicies(null);
  }

  /**
   * Set retention policy.
   *
   * Set the retention policy for the specified namespace.
   *
   * @param setRetentionPolicyOptions the {@link SetRetentionPolicyOptions} containing the options for the call
   * @return a {@link ServiceCall} with a void result
   */
  public ServiceCall<Void> setRetentionPolicy(SetRetentionPolicyOptions setRetentionPolicyOptions) {
    com.ibm.cloud.sdk.core.util.Validator.notNull(setRetentionPolicyOptions,
      "setRetentionPolicyOptions cannot be null");
    RequestBuilder builder = RequestBuilder.post(RequestBuilder.resolveRequestUrl(getServiceUrl(), "/api/v1/retentions"));
    Map<String, String> sdkHeaders = SdkCommon.getSdkHeaders("container_registry", "v1", "setRetentionPolicy");
    for (Entry<String, String> header : sdkHeaders.entrySet()) {
      builder.header(header.getKey(), header.getValue());
    }
    builder.header("Account", this.account);
    final JsonObject contentJson = new JsonObject();
    contentJson.addProperty("namespace", setRetentionPolicyOptions.namespace());
    if (setRetentionPolicyOptions.imagesPerRepo() != null) {
      contentJson.addProperty("images_per_repo", setRetentionPolicyOptions.imagesPerRepo());
    }
    if (setRetentionPolicyOptions.retainUntagged() != null) {
      contentJson.addProperty("retain_untagged", setRetentionPolicyOptions.retainUntagged());
    }
    builder.bodyJson(contentJson);
    ResponseConverter<Void> responseConverter = ResponseConverterUtils.getVoid();
    return createServiceCall(builder.build(), responseConverter);
  }

  /**
   * Analyze retention policy.
   *
   * Analyze a retention policy, and get a list of what would be deleted by it.
   *
   * @param analyzeRetentionPolicyOptions the {@link AnalyzeRetentionPolicyOptions} containing the options for the call
   * @return a {@link ServiceCall} with a result of type {@link Map}
   */
  public ServiceCall<Map<String, List<String>>> analyzeRetentionPolicy(AnalyzeRetentionPolicyOptions analyzeRetentionPolicyOptions) {
    com.ibm.cloud.sdk.core.util.Validator.notNull(analyzeRetentionPolicyOptions,
      "analyzeRetentionPolicyOptions cannot be null");
    RequestBuilder builder = RequestBuilder.post(RequestBuilder.resolveRequestUrl(getServiceUrl(), "/api/v1/retentions/analyze"));
    Map<String, String> sdkHeaders = SdkCommon.getSdkHeaders("container_registry", "v1", "analyzeRetentionPolicy");
    for (Entry<String, String> header : sdkHeaders.entrySet()) {
      builder.header(header.getKey(), header.getValue());
    }
    builder.header("Accept", "application/json");
    builder.header("Account", this.account);
    final JsonObject contentJson = new JsonObject();
    contentJson.addProperty("namespace", analyzeRetentionPolicyOptions.namespace());
    if (analyzeRetentionPolicyOptions.imagesPerRepo() != null) {
      contentJson.addProperty("images_per_repo", analyzeRetentionPolicyOptions.imagesPerRepo());
    }
    if (analyzeRetentionPolicyOptions.retainUntagged() != null) {
      contentJson.addProperty("retain_untagged", analyzeRetentionPolicyOptions.retainUntagged());
    }
    builder.bodyJson(contentJson);
    ResponseConverter<Map<String, List<String>>> responseConverter =
      ResponseConverterUtils.getValue(new com.google.gson.reflect.TypeToken<Map<String, List<String>>>() { }.getType());
    return createServiceCall(builder.build(), responseConverter);
  }

  /**
   * Get retention policy.
   *
   * Get the retention policy for the specified namespace.
   *
   * @param getRetentionPolicyOptions the {@link GetRetentionPolicyOptions} containing the options for the call
   * @return a {@link ServiceCall} with a result of type {@link RetentionPolicy}
   */
  public ServiceCall<RetentionPolicy> getRetentionPolicy(GetRetentionPolicyOptions getRetentionPolicyOptions) {
    com.ibm.cloud.sdk.core.util.Validator.notNull(getRetentionPolicyOptions,
      "getRetentionPolicyOptions cannot be null");
    Map<String, String> pathParamsMap = new HashMap<String, String>();
    pathParamsMap.put("namespace", getRetentionPolicyOptions.namespace());
    RequestBuilder builder = RequestBuilder.get(RequestBuilder.resolveRequestUrl(getServiceUrl(), "/api/v1/retentions/{namespace}", pathParamsMap));
    Map<String, String> sdkHeaders = SdkCommon.getSdkHeaders("container_registry", "v1", "getRetentionPolicy");
    for (Entry<String, String> header : sdkHeaders.entrySet()) {
      builder.header(header.getKey(), header.getValue());
    }
    builder.header("Accept", "application/json");
    builder.header("Account", this.account);
    ResponseConverter<RetentionPolicy> responseConverter =
      ResponseConverterUtils.getValue(new com.google.gson.reflect.TypeToken<RetentionPolicy>() { }.getType());
    return createServiceCall(builder.build(), responseConverter);
  }

  /**
   * Get account settings.
   *
   * Get account settings for the targeted account.
   *
   * @param getSettingsOptions the {@link GetSettingsOptions} containing the options for the call
   * @return a {@link ServiceCall} with a result of type {@link AccountSettings}
   */
  public ServiceCall<AccountSettings> getSettings(GetSettingsOptions getSettingsOptions) {
    RequestBuilder builder = RequestBuilder.get(RequestBuilder.resolveRequestUrl(getServiceUrl(), "/api/v1/settings"));
    Map<String, String> sdkHeaders = SdkCommon.getSdkHeaders("container_registry", "v1", "getSettings");
    for (Entry<String, String> header : sdkHeaders.entrySet()) {
      builder.header(header.getKey(), header.getValue());
    }
    builder.header("Accept", "application/json");
    builder.header("Account", this.account);
    ResponseConverter<AccountSettings> responseConverter =
      ResponseConverterUtils.getValue(new com.google.gson.reflect.TypeToken<AccountSettings>() { }.getType());
    return createServiceCall(builder.build(), responseConverter);
  }

  /**
   * Get account settings.
   *
   * Get account settings for the targeted account.
   *
   * @return a {@link ServiceCall} with a result of type {@link AccountSettings}
   */
  public ServiceCall<AccountSettings> getSettings() {
    return getSettings(null);
  }

  /**
   * Update account settings.
   *
   * Update settings for the targeted account.
   *
   * @param updateSettingsOptions the {@link UpdateSettingsOptions} containing the options for the call
   * @return a {@link ServiceCall} with a void result
   */
  public ServiceCall<Void> updateSettings(UpdateSettingsOptions updateSettingsOptions) {
    com.ibm.cloud.sdk.core.util.Validator.notNull(updateSettingsOptions,
      "updateSettingsOptions cannot be null");
    RequestBuilder builder = RequestBuilder.patch(RequestBuilder.resolveRequestUrl(getServiceUrl(), "/api/v1/settings"));
    Map<String, String> sdkHeaders = SdkCommon.getSdkHeaders("container_registry", "v1", "updateSettings");
    for (Entry<String, String> header : sdkHeaders.entrySet()) {
      builder.header(header.getKey(), header.getValue());
    }
    builder.header("Account", this.account);
    final JsonObject contentJson = new JsonObject();
    if (updateSettingsOptions.platformMetrics() != null) {
      contentJson.addProperty("platform_metrics", updateSettingsOptions.platformMetrics());
    }
    builder.bodyJson(contentJson);
    ResponseConverter<Void> responseConverter = ResponseConverterUtils.getVoid();
    return createServiceCall(builder.build(), responseConverter);
  }

  /**
   * Update account settings.
   *
   * Update settings for the targeted account.
   *
   * @return a {@link ServiceCall} with a void result
   */
  public ServiceCall<Void> updateSettings() {
    return updateSettings(null);
  }

  /**
   * Delete tag.
   *
   * Untag a container image in the registry.
   *
   * @param deleteImageTagOptions the {@link DeleteImageTagOptions} containing the options for the call
   * @return a {@link ServiceCall} with a result of type {@link ImageDeleteResult}
   */
  public ServiceCall<ImageDeleteResult> deleteImageTag(DeleteImageTagOptions deleteImageTagOptions) {
    com.ibm.cloud.sdk.core.util.Validator.notNull(deleteImageTagOptions,
      "deleteImageTagOptions cannot be null");
    Map<String, String> pathParamsMap = new HashMap<String, String>();
    pathParamsMap.put("image", deleteImageTagOptions.image());
    RequestBuilder builder = RequestBuilder.delete(RequestBuilder.resolveRequestUrl(getServiceUrl(), "/api/v1/tags/{image}", pathParamsMap));
    Map<String, String> sdkHeaders = SdkCommon.getSdkHeaders("container_registry", "v1", "deleteImageTag");
    for (Entry<String, String> header : sdkHeaders.entrySet()) {
      builder.header(header.getKey(), header.getValue());
    }
    builder.header("Accept", "application/json");
    builder.header("Account", this.account);
    ResponseConverter<ImageDeleteResult> responseConverter =
      ResponseConverterUtils.getValue(new com.google.gson.reflect.TypeToken<ImageDeleteResult>() { }.getType());
    return createServiceCall(builder.build(), responseConverter);
  }

  /**
   * List deleted images.
   *
   * List all images that are in the trash can.
   *
   * @param listDeletedImagesOptions the {@link ListDeletedImagesOptions} containing the options for the call
   * @return a {@link ServiceCall} with a result of type {@link Map}
   */
  public ServiceCall<Map<String, Trash>> listDeletedImages(ListDeletedImagesOptions listDeletedImagesOptions) {
    if (listDeletedImagesOptions == null) {
      listDeletedImagesOptions = new ListDeletedImagesOptions.Builder().build();
    }
    RequestBuilder builder = RequestBuilder.get(RequestBuilder.resolveRequestUrl(getServiceUrl(), "/api/v1/trash"));
    Map<String, String> sdkHeaders = SdkCommon.getSdkHeaders("container_registry", "v1", "listDeletedImages");
    for (Entry<String, String> header : sdkHeaders.entrySet()) {
      builder.header(header.getKey(), header.getValue());
    }
    builder.header("Accept", "application/json");
    builder.header("Account", this.account);
    if (listDeletedImagesOptions.namespace() != null) {
      builder.query("namespace", String.valueOf(listDeletedImagesOptions.namespace()));
    }
    ResponseConverter<Map<String, Trash>> responseConverter =
      ResponseConverterUtils.getValue(new com.google.gson.reflect.TypeToken<Map<String, Trash>>() { }.getType());
    return createServiceCall(builder.build(), responseConverter);
  }

  /**
   * List deleted images.
   *
   * List all images that are in the trash can.
   *
   * @return a {@link ServiceCall} with a result of type {@link Map}
   */
  public ServiceCall<Map<String, Trash>> listDeletedImages() {
    return listDeletedImages(null);
  }

  /**
   * Restore a digest and all associated tags.
   *
   * In the targeted region, restore a digest, and all of its tags in the same repository, from the trash.
   *
   * @param restoreTagsOptions the {@link RestoreTagsOptions} containing the options for the call
   * @return a {@link ServiceCall} with a result of type {@link RestoreResult}
   */
  public ServiceCall<RestoreResult> restoreTags(RestoreTagsOptions restoreTagsOptions) {
    com.ibm.cloud.sdk.core.util.Validator.notNull(restoreTagsOptions,
      "restoreTagsOptions cannot be null");
    Map<String, String> pathParamsMap = new HashMap<String, String>();
    pathParamsMap.put("digest", restoreTagsOptions.digest());
    RequestBuilder builder = RequestBuilder.post(RequestBuilder.resolveRequestUrl(getServiceUrl(), "/api/v1/trash/{digest}/restoretags", pathParamsMap));
    Map<String, String> sdkHeaders = SdkCommon.getSdkHeaders("container_registry", "v1", "restoreTags");
    for (Entry<String, String> header : sdkHeaders.entrySet()) {
      builder.header(header.getKey(), header.getValue());
    }
    builder.header("Accept", "application/json");
    builder.header("Account", this.account);
    ResponseConverter<RestoreResult> responseConverter =
      ResponseConverterUtils.getValue(new com.google.gson.reflect.TypeToken<RestoreResult>() { }.getType());
    return createServiceCall(builder.build(), responseConverter);
  }

  /**
   * Restore deleted image.
   *
   * Restore an image from the trash can.
   *
   * @param restoreImageOptions the {@link RestoreImageOptions} containing the options for the call
   * @return a {@link ServiceCall} with a void result
   */
  public ServiceCall<Void> restoreImage(RestoreImageOptions restoreImageOptions) {
    com.ibm.cloud.sdk.core.util.Validator.notNull(restoreImageOptions,
      "restoreImageOptions cannot be null");
    Map<String, String> pathParamsMap = new HashMap<String, String>();
    pathParamsMap.put("image", restoreImageOptions.image());
    RequestBuilder builder = RequestBuilder.post(RequestBuilder.resolveRequestUrl(getServiceUrl(), "/api/v1/trash/{image}/restore", pathParamsMap));
    Map<String, String> sdkHeaders = SdkCommon.getSdkHeaders("container_registry", "v1", "restoreImage");
    for (Entry<String, String> header : sdkHeaders.entrySet()) {
      builder.header(header.getKey(), header.getValue());
    }
    builder.header("Account", this.account);
    ResponseConverter<Void> responseConverter = ResponseConverterUtils.getVoid();
    return createServiceCall(builder.build(), responseConverter);
  }

}
