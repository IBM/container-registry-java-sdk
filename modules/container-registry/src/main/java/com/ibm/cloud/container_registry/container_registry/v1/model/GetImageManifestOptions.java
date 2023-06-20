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
 * The getImageManifest options.
 */
public class GetImageManifestOptions extends GenericModel {

  protected String image;

  /**
   * Builder.
   */
  public static class Builder {
    private String image;

    /**
     * Instantiates a new Builder from an existing GetImageManifestOptions instance.
     *
     * @param getImageManifestOptions the instance to initialize the Builder with
     */
    private Builder(GetImageManifestOptions getImageManifestOptions) {
      this.image = getImageManifestOptions.image;
    }

    /**
     * Instantiates a new builder.
     */
    public Builder() {
    }

    /**
     * Instantiates a new builder with required properties.
     *
     * @param image the image
     */
    public Builder(String image) {
      this.image = image;
    }

    /**
     * Builds a GetImageManifestOptions.
     *
     * @return the new GetImageManifestOptions instance
     */
    public GetImageManifestOptions build() {
      return new GetImageManifestOptions(this);
    }

    /**
     * Set the image.
     *
     * @param image the image
     * @return the GetImageManifestOptions builder
     */
    public Builder image(String image) {
      this.image = image;
      return this;
    }
  }

  protected GetImageManifestOptions() { }

  protected GetImageManifestOptions(Builder builder) {
    com.ibm.cloud.sdk.core.util.Validator.notEmpty(builder.image,
      "image cannot be empty");
    image = builder.image;
  }

  /**
   * New builder.
   *
   * @return a GetImageManifestOptions builder
   */
  public Builder newBuilder() {
    return new Builder(this);
  }

  /**
   * Gets the image.
   *
   * The full IBM Cloud registry path to the image that you want to inspect. Run `ibmcloud cr images` or call the `GET
   * /images/json` endpoint to review images that are in the registry.
   *
   * @return the image
   */
  public String image() {
    return image;
  }
}

