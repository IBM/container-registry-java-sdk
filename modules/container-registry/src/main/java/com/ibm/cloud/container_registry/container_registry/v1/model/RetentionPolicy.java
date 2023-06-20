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

import com.google.gson.annotations.SerializedName;
import com.ibm.cloud.sdk.core.service.model.GenericModel;

/**
 * A document that contains the image retention settings for a namespace.
 */
public class RetentionPolicy extends GenericModel {

  @SerializedName("images_per_repo")
  protected Long imagesPerRepo;
  protected String namespace;
  @SerializedName("retain_untagged")
  protected Boolean retainUntagged;

  /**
   * Builder.
   */
  public static class Builder {
    private Long imagesPerRepo;
    private String namespace;
    private Boolean retainUntagged;

    /**
     * Instantiates a new Builder from an existing RetentionPolicy instance.
     *
     * @param retentionPolicy the instance to initialize the Builder with
     */
    private Builder(RetentionPolicy retentionPolicy) {
      this.imagesPerRepo = retentionPolicy.imagesPerRepo;
      this.namespace = retentionPolicy.namespace;
      this.retainUntagged = retentionPolicy.retainUntagged;
    }

    /**
     * Instantiates a new builder.
     */
    public Builder() {
    }

    /**
     * Instantiates a new builder with required properties.
     *
     * @param namespace the namespace
     */
    public Builder(String namespace) {
      this.namespace = namespace;
    }

    /**
     * Builds a RetentionPolicy.
     *
     * @return the new RetentionPolicy instance
     */
    public RetentionPolicy build() {
      return new RetentionPolicy(this);
    }

    /**
     * Set the imagesPerRepo.
     *
     * @param imagesPerRepo the imagesPerRepo
     * @return the RetentionPolicy builder
     */
    public Builder imagesPerRepo(long imagesPerRepo) {
      this.imagesPerRepo = imagesPerRepo;
      return this;
    }

    /**
     * Set the namespace.
     *
     * @param namespace the namespace
     * @return the RetentionPolicy builder
     */
    public Builder namespace(String namespace) {
      this.namespace = namespace;
      return this;
    }

    /**
     * Set the retainUntagged.
     *
     * @param retainUntagged the retainUntagged
     * @return the RetentionPolicy builder
     */
    public Builder retainUntagged(Boolean retainUntagged) {
      this.retainUntagged = retainUntagged;
      return this;
    }
  }

  protected RetentionPolicy() { }

  protected RetentionPolicy(Builder builder) {
    com.ibm.cloud.sdk.core.util.Validator.notNull(builder.namespace,
      "namespace cannot be null");
    imagesPerRepo = builder.imagesPerRepo;
    namespace = builder.namespace;
    retainUntagged = builder.retainUntagged;
  }

  /**
   * New builder.
   *
   * @return a RetentionPolicy builder
   */
  public Builder newBuilder() {
    return new Builder(this);
  }

  /**
   * Gets the imagesPerRepo.
   *
   * Determines how many images are retained in each repository when the retention policy is processed. The value -1
   * denotes 'Unlimited' (all images are retained).
   *
   * @return the imagesPerRepo
   */
  public Long imagesPerRepo() {
    return imagesPerRepo;
  }

  /**
   * Gets the namespace.
   *
   * The namespace to which the retention policy is attached.
   *
   * @return the namespace
   */
  public String namespace() {
    return namespace;
  }

  /**
   * Gets the retainUntagged.
   *
   * Determines whether untagged images are retained when the retention policy is processed. The value is false by
   * default, which means that  untagged images can be deleted when the policy runs.
   *
   * @return the retainUntagged
   */
  public Boolean retainUntagged() {
    return retainUntagged;
  }
}

