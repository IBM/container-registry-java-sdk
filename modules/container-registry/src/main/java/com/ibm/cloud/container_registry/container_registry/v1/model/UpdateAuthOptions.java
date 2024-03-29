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
 * The updateAuth options.
 */
public class UpdateAuthOptions extends GenericModel {

  protected Boolean iamAuthz;
  protected Boolean privateOnly;

  /**
   * Builder.
   */
  public static class Builder {
    private Boolean iamAuthz;
    private Boolean privateOnly;

    /**
     * Instantiates a new Builder from an existing UpdateAuthOptions instance.
     *
     * @param updateAuthOptions the instance to initialize the Builder with
     */
    private Builder(UpdateAuthOptions updateAuthOptions) {
      this.iamAuthz = updateAuthOptions.iamAuthz;
      this.privateOnly = updateAuthOptions.privateOnly;
    }

    /**
     * Instantiates a new builder.
     */
    public Builder() {
    }

    /**
     * Builds a UpdateAuthOptions.
     *
     * @return the new UpdateAuthOptions instance
     */
    public UpdateAuthOptions build() {
      return new UpdateAuthOptions(this);
    }

    /**
     * Set the iamAuthz.
     *
     * @param iamAuthz the iamAuthz
     * @return the UpdateAuthOptions builder
     */
    public Builder iamAuthz(Boolean iamAuthz) {
      this.iamAuthz = iamAuthz;
      return this;
    }

    /**
     * Set the privateOnly.
     *
     * @param privateOnly the privateOnly
     * @return the UpdateAuthOptions builder
     */
    public Builder privateOnly(Boolean privateOnly) {
      this.privateOnly = privateOnly;
      return this;
    }

    /**
     * Set the authOptions.
     *
     * @param authOptions the authOptions
     * @return the UpdateAuthOptions builder
     */
    public Builder authOptions(AuthOptions authOptions) {
      this.iamAuthz = authOptions.iamAuthz();
      this.privateOnly = authOptions.privateOnly();
      return this;
    }
  }

  protected UpdateAuthOptions() { }

  protected UpdateAuthOptions(Builder builder) {
    iamAuthz = builder.iamAuthz;
    privateOnly = builder.privateOnly;
  }

  /**
   * New builder.
   *
   * @return a UpdateAuthOptions builder
   */
  public Builder newBuilder() {
    return new Builder(this);
  }

  /**
   * Gets the iamAuthz.
   *
   * Enable role based authorization when authenticating with IBM Cloud IAM.
   *
   * @return the iamAuthz
   */
  public Boolean iamAuthz() {
    return iamAuthz;
  }

  /**
   * Gets the privateOnly.
   *
   * Restrict account to only be able to push and pull images over private connections.
   *
   * @return the privateOnly
   */
  public Boolean privateOnly() {
    return privateOnly;
  }
}

