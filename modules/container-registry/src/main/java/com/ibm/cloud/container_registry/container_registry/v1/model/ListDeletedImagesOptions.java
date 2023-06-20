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
 * The listDeletedImages options.
 */
public class ListDeletedImagesOptions extends GenericModel {

  protected String namespace;

  /**
   * Builder.
   */
  public static class Builder {
    private String namespace;

    /**
     * Instantiates a new Builder from an existing ListDeletedImagesOptions instance.
     *
     * @param listDeletedImagesOptions the instance to initialize the Builder with
     */
    private Builder(ListDeletedImagesOptions listDeletedImagesOptions) {
      this.namespace = listDeletedImagesOptions.namespace;
    }

    /**
     * Instantiates a new builder.
     */
    public Builder() {
    }

    /**
     * Builds a ListDeletedImagesOptions.
     *
     * @return the new ListDeletedImagesOptions instance
     */
    public ListDeletedImagesOptions build() {
      return new ListDeletedImagesOptions(this);
    }

    /**
     * Set the namespace.
     *
     * @param namespace the namespace
     * @return the ListDeletedImagesOptions builder
     */
    public Builder namespace(String namespace) {
      this.namespace = namespace;
      return this;
    }
  }

  protected ListDeletedImagesOptions() { }

  protected ListDeletedImagesOptions(Builder builder) {
    namespace = builder.namespace;
  }

  /**
   * New builder.
   *
   * @return a ListDeletedImagesOptions builder
   */
  public Builder newBuilder() {
    return new Builder(this);
  }

  /**
   * Gets the namespace.
   *
   * Limit results to trash can images in the given namespace only.
   *
   * @return the namespace
   */
  public String namespace() {
    return namespace;
  }
}

