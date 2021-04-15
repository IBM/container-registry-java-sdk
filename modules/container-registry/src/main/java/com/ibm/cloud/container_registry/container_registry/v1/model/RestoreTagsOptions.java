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
package com.ibm.cloud.container_registry.container_registry.v1.model;

import com.ibm.cloud.sdk.core.service.model.GenericModel;

/**
 * The restoreTags options.
 */
public class RestoreTagsOptions extends GenericModel {

  protected String digest;

  /**
   * Builder.
   */
  public static class Builder {
    private String digest;

    private Builder(RestoreTagsOptions restoreTagsOptions) {
      this.digest = restoreTagsOptions.digest;
    }

    /**
     * Instantiates a new builder.
     */
    public Builder() {
    }

    /**
     * Instantiates a new builder with required properties.
     *
     * @param digest the digest
     */
    public Builder(String digest) {
      this.digest = digest;
    }

    /**
     * Builds a RestoreTagsOptions.
     *
     * @return the new RestoreTagsOptions instance
     */
    public RestoreTagsOptions build() {
      return new RestoreTagsOptions(this);
    }

    /**
     * Set the digest.
     *
     * @param digest the digest
     * @return the RestoreTagsOptions builder
     */
    public Builder digest(String digest) {
      this.digest = digest;
      return this;
    }
  }

  protected RestoreTagsOptions(Builder builder) {
    com.ibm.cloud.sdk.core.util.Validator.notEmpty(builder.digest,
      "digest cannot be empty");
    digest = builder.digest;
  }

  /**
   * New builder.
   *
   * @return a RestoreTagsOptions builder
   */
  public Builder newBuilder() {
    return new Builder(this);
  }

  /**
   * Gets the digest.
   *
   * The full IBM Cloud registry digest reference for the digest that you want to restore such as
   * `icr.io/namespace/repo@sha256:a9be...`. Call the `GET /trash/json` endpoint to review digests that are in the trash
   * and their tags in the same repository.
   *
   * @return the digest
   */
  public String digest() {
    return digest;
  }
}

