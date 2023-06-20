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
package com.ibm.cloud.container_registry.container_registry.v1.model;

import com.ibm.cloud.sdk.core.service.model.GenericModel;

/**
 * The listImages options.
 */
public class ListImagesOptions extends GenericModel {

  protected String namespace;
  protected Boolean includeIbm;
  protected Boolean includePrivate;
  protected Boolean includeManifestLists;
  protected Boolean vulnerabilities;
  protected String repository;

  /**
   * Builder.
   */
  public static class Builder {
    private String namespace;
    private Boolean includeIbm;
    private Boolean includePrivate;
    private Boolean includeManifestLists;
    private Boolean vulnerabilities;
    private String repository;

    /**
     * Instantiates a new Builder from an existing ListImagesOptions instance.
     *
     * @param listImagesOptions the instance to initialize the Builder with
     */
    private Builder(ListImagesOptions listImagesOptions) {
      this.namespace = listImagesOptions.namespace;
      this.includeIbm = listImagesOptions.includeIbm;
      this.includePrivate = listImagesOptions.includePrivate;
      this.includeManifestLists = listImagesOptions.includeManifestLists;
      this.vulnerabilities = listImagesOptions.vulnerabilities;
      this.repository = listImagesOptions.repository;
    }

    /**
     * Instantiates a new builder.
     */
    public Builder() {
    }

    /**
     * Builds a ListImagesOptions.
     *
     * @return the new ListImagesOptions instance
     */
    public ListImagesOptions build() {
      return new ListImagesOptions(this);
    }

    /**
     * Set the namespace.
     *
     * @param namespace the namespace
     * @return the ListImagesOptions builder
     */
    public Builder namespace(String namespace) {
      this.namespace = namespace;
      return this;
    }

    /**
     * Set the includeIbm.
     *
     * @param includeIbm the includeIbm
     * @return the ListImagesOptions builder
     */
    public Builder includeIbm(Boolean includeIbm) {
      this.includeIbm = includeIbm;
      return this;
    }

    /**
     * Set the includePrivate.
     *
     * @param includePrivate the includePrivate
     * @return the ListImagesOptions builder
     */
    public Builder includePrivate(Boolean includePrivate) {
      this.includePrivate = includePrivate;
      return this;
    }

    /**
     * Set the includeManifestLists.
     *
     * @param includeManifestLists the includeManifestLists
     * @return the ListImagesOptions builder
     */
    public Builder includeManifestLists(Boolean includeManifestLists) {
      this.includeManifestLists = includeManifestLists;
      return this;
    }

    /**
     * Set the vulnerabilities.
     *
     * @param vulnerabilities the vulnerabilities
     * @return the ListImagesOptions builder
     */
    public Builder vulnerabilities(Boolean vulnerabilities) {
      this.vulnerabilities = vulnerabilities;
      return this;
    }

    /**
     * Set the repository.
     *
     * @param repository the repository
     * @return the ListImagesOptions builder
     */
    public Builder repository(String repository) {
      this.repository = repository;
      return this;
    }
  }

  protected ListImagesOptions() { }

  protected ListImagesOptions(Builder builder) {
    namespace = builder.namespace;
    includeIbm = builder.includeIbm;
    includePrivate = builder.includePrivate;
    includeManifestLists = builder.includeManifestLists;
    vulnerabilities = builder.vulnerabilities;
    repository = builder.repository;
  }

  /**
   * New builder.
   *
   * @return a ListImagesOptions builder
   */
  public Builder newBuilder() {
    return new Builder(this);
  }

  /**
   * Gets the namespace.
   *
   * Lists images that are stored in the specified namespace only. Query multiple namespaces by specifying this option
   * for each namespace. If this option is not specified, images from all namespaces in the specified IBM Cloud account
   * are listed.
   *
   * @return the namespace
   */
  public String namespace() {
    return namespace;
  }

  /**
   * Gets the includeIbm.
   *
   * Includes IBM-provided public images in the list of images. If this option is not specified, private images are
   * listed only. If this option is specified more than once, the last parsed setting is the setting that is used.
   *
   * @return the includeIbm
   */
  public Boolean includeIbm() {
    return includeIbm;
  }

  /**
   * Gets the includePrivate.
   *
   * Includes private images in the list of images. If this option is not specified, private images are listed. If this
   * option is specified more than once, the last parsed setting is the setting that is used.
   *
   * @return the includePrivate
   */
  public Boolean includePrivate() {
    return includePrivate;
  }

  /**
   * Gets the includeManifestLists.
   *
   * Includes tags that reference multi-architecture manifest lists in the image list. If this option is not specified,
   * tagged manifest lists are not shown in the list. If this option is specified more than once, the last parsed
   * setting is the setting that is used.
   *
   * @return the includeManifestLists
   */
  public Boolean includeManifestLists() {
    return includeManifestLists;
  }

  /**
   * Gets the vulnerabilities.
   *
   * Displays Vulnerability Advisor status for the listed images. If this option is specified more than once, the last
   * parsed setting is the setting that is used.
   *
   * @return the vulnerabilities
   */
  public Boolean vulnerabilities() {
    return vulnerabilities;
  }

  /**
   * Gets the repository.
   *
   * Lists images that are stored in the specified repository, under your namespaces. Query multiple repositories by
   * specifying this option for each repository. If this option is not specified, images from all repos are listed.
   *
   * @return the repository
   */
  public String repository() {
    return repository;
  }
}

