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
 * The setRetentionPolicy options.
 */
public class SetRetentionPolicyOptions extends GenericModel {

  protected String namespace;
  protected Long imagesPerRepo;
  protected Boolean retainUntagged;

  /**
   * Builder.
   */
  public static class Builder {
    private String namespace;
    private Long imagesPerRepo;
    private Boolean retainUntagged;

    /**
     * Instantiates a new Builder from an existing SetRetentionPolicyOptions instance.
     *
     * @param setRetentionPolicyOptions the instance to initialize the Builder with
     */
    private Builder(SetRetentionPolicyOptions setRetentionPolicyOptions) {
      this.namespace = setRetentionPolicyOptions.namespace;
      this.imagesPerRepo = setRetentionPolicyOptions.imagesPerRepo;
      this.retainUntagged = setRetentionPolicyOptions.retainUntagged;
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
     * Builds a SetRetentionPolicyOptions.
     *
     * @return the new SetRetentionPolicyOptions instance
     */
    public SetRetentionPolicyOptions build() {
      return new SetRetentionPolicyOptions(this);
    }

    /**
     * Set the namespace.
     *
     * @param namespace the namespace
     * @return the SetRetentionPolicyOptions builder
     */
    public Builder namespace(String namespace) {
      this.namespace = namespace;
      return this;
    }

    /**
     * Set the imagesPerRepo.
     *
     * @param imagesPerRepo the imagesPerRepo
     * @return the SetRetentionPolicyOptions builder
     */
    public Builder imagesPerRepo(long imagesPerRepo) {
      this.imagesPerRepo = imagesPerRepo;
      return this;
    }

    /**
     * Set the retainUntagged.
     *
     * @param retainUntagged the retainUntagged
     * @return the SetRetentionPolicyOptions builder
     */
    public Builder retainUntagged(Boolean retainUntagged) {
      this.retainUntagged = retainUntagged;
      return this;
    }

    /**
     * Set the retentionPolicy.
     *
     * @param retentionPolicy the retentionPolicy
     * @return the SetRetentionPolicyOptions builder
     */
    public Builder retentionPolicy(RetentionPolicy retentionPolicy) {
      this.namespace = retentionPolicy.namespace();
      this.imagesPerRepo = retentionPolicy.imagesPerRepo();
      this.retainUntagged = retentionPolicy.retainUntagged();
      return this;
    }
  }

  protected SetRetentionPolicyOptions() { }

  protected SetRetentionPolicyOptions(Builder builder) {
    com.ibm.cloud.sdk.core.util.Validator.notNull(builder.namespace,
      "namespace cannot be null");
    namespace = builder.namespace;
    imagesPerRepo = builder.imagesPerRepo;
    retainUntagged = builder.retainUntagged;
  }

  /**
   * New builder.
   *
   * @return a SetRetentionPolicyOptions builder
   */
  public Builder newBuilder() {
    return new Builder(this);
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

