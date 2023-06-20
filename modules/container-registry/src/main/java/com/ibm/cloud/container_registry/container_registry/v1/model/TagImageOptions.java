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
 * The tagImage options.
 */
public class TagImageOptions extends GenericModel {

  protected String fromimage;
  protected String toimage;

  /**
   * Builder.
   */
  public static class Builder {
    private String fromimage;
    private String toimage;

    /**
     * Instantiates a new Builder from an existing TagImageOptions instance.
     *
     * @param tagImageOptions the instance to initialize the Builder with
     */
    private Builder(TagImageOptions tagImageOptions) {
      this.fromimage = tagImageOptions.fromimage;
      this.toimage = tagImageOptions.toimage;
    }

    /**
     * Instantiates a new builder.
     */
    public Builder() {
    }

    /**
     * Instantiates a new builder with required properties.
     *
     * @param fromimage the fromimage
     * @param toimage the toimage
     */
    public Builder(String fromimage, String toimage) {
      this.fromimage = fromimage;
      this.toimage = toimage;
    }

    /**
     * Builds a TagImageOptions.
     *
     * @return the new TagImageOptions instance
     */
    public TagImageOptions build() {
      return new TagImageOptions(this);
    }

    /**
     * Set the fromimage.
     *
     * @param fromimage the fromimage
     * @return the TagImageOptions builder
     */
    public Builder fromimage(String fromimage) {
      this.fromimage = fromimage;
      return this;
    }

    /**
     * Set the toimage.
     *
     * @param toimage the toimage
     * @return the TagImageOptions builder
     */
    public Builder toimage(String toimage) {
      this.toimage = toimage;
      return this;
    }
  }

  protected TagImageOptions() { }

  protected TagImageOptions(Builder builder) {
    com.ibm.cloud.sdk.core.util.Validator.notNull(builder.fromimage,
      "fromimage cannot be null");
    com.ibm.cloud.sdk.core.util.Validator.notNull(builder.toimage,
      "toimage cannot be null");
    fromimage = builder.fromimage;
    toimage = builder.toimage;
  }

  /**
   * New builder.
   *
   * @return a TagImageOptions builder
   */
  public Builder newBuilder() {
    return new Builder(this);
  }

  /**
   * Gets the fromimage.
   *
   * The name of the image that you want to create a new tag for, in the format
   * &amp;lt;REPOSITORY&amp;gt;:&amp;lt;TAG&amp;gt;. Run `ibmcloud cr images` or call the `GET /images/json` endpoint to
   * review images that are in the registry.
   *
   * @return the fromimage
   */
  public String fromimage() {
    return fromimage;
  }

  /**
   * Gets the toimage.
   *
   * The new tag for the image, in the format &amp;lt;REPOSITORY&amp;gt;:&amp;lt;TAG&amp;gt;.
   *
   * @return the toimage
   */
  public String toimage() {
    return toimage;
  }
}

