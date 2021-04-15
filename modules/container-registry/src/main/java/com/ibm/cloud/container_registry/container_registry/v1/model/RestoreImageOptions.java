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
 * The restoreImage options.
 */
public class RestoreImageOptions extends GenericModel {

  protected String image;

  /**
   * Builder.
   */
  public static class Builder {
    private String image;

    private Builder(RestoreImageOptions restoreImageOptions) {
      this.image = restoreImageOptions.image;
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
     * Builds a RestoreImageOptions.
     *
     * @return the new RestoreImageOptions instance
     */
    public RestoreImageOptions build() {
      return new RestoreImageOptions(this);
    }

    /**
     * Set the image.
     *
     * @param image the image
     * @return the RestoreImageOptions builder
     */
    public Builder image(String image) {
      this.image = image;
      return this;
    }
  }

  protected RestoreImageOptions(Builder builder) {
    com.ibm.cloud.sdk.core.util.Validator.notEmpty(builder.image,
      "image cannot be empty");
    image = builder.image;
  }

  /**
   * New builder.
   *
   * @return a RestoreImageOptions builder
   */
  public Builder newBuilder() {
    return new Builder(this);
  }

  /**
   * Gets the image.
   *
   * The name of the image that you want to restore, in the format &amp;lt;REPOSITORY&amp;gt;:&amp;lt;TAG&amp;gt;. Run
   * `ibmcloud cr trash-list` or call the `GET /trash/json` endpoint to review images that are in the trash.
   *
   * @return the image
   */
  public String image() {
    return image;
  }
}

