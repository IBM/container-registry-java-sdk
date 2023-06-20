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
 * The getRetentionPolicy options.
 */
public class GetRetentionPolicyOptions extends GenericModel {

  protected String namespace;

  /**
   * Builder.
   */
  public static class Builder {
    private String namespace;

    /**
     * Instantiates a new Builder from an existing GetRetentionPolicyOptions instance.
     *
     * @param getRetentionPolicyOptions the instance to initialize the Builder with
     */
    private Builder(GetRetentionPolicyOptions getRetentionPolicyOptions) {
      this.namespace = getRetentionPolicyOptions.namespace;
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
     * Builds a GetRetentionPolicyOptions.
     *
     * @return the new GetRetentionPolicyOptions instance
     */
    public GetRetentionPolicyOptions build() {
      return new GetRetentionPolicyOptions(this);
    }

    /**
     * Set the namespace.
     *
     * @param namespace the namespace
     * @return the GetRetentionPolicyOptions builder
     */
    public Builder namespace(String namespace) {
      this.namespace = namespace;
      return this;
    }
  }

  protected GetRetentionPolicyOptions() { }

  protected GetRetentionPolicyOptions(Builder builder) {
    com.ibm.cloud.sdk.core.util.Validator.notEmpty(builder.namespace,
      "namespace cannot be empty");
    namespace = builder.namespace;
  }

  /**
   * New builder.
   *
   * @return a GetRetentionPolicyOptions builder
   */
  public Builder newBuilder() {
    return new Builder(this);
  }

  /**
   * Gets the namespace.
   *
   * Gets the retention policy for the specified namespace.
   *
   * @return the namespace
   */
  public String namespace() {
    return namespace;
  }
}

