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

import java.util.ArrayList;
import java.util.List;

import com.ibm.cloud.sdk.core.service.model.GenericModel;

/**
 * The bulkDeleteImages options.
 */
public class BulkDeleteImagesOptions extends GenericModel {

  protected List<String> bulkDelete;

  /**
   * Builder.
   */
  public static class Builder {
    private List<String> bulkDelete;

    /**
     * Instantiates a new Builder from an existing BulkDeleteImagesOptions instance.
     *
     * @param bulkDeleteImagesOptions the instance to initialize the Builder with
     */
    private Builder(BulkDeleteImagesOptions bulkDeleteImagesOptions) {
      this.bulkDelete = bulkDeleteImagesOptions.bulkDelete;
    }

    /**
     * Instantiates a new builder.
     */
    public Builder() {
    }

    /**
     * Instantiates a new builder with required properties.
     *
     * @param bulkDelete the bulkDelete
     */
    public Builder(List<String> bulkDelete) {
      this.bulkDelete = bulkDelete;
    }

    /**
     * Builds a BulkDeleteImagesOptions.
     *
     * @return the new BulkDeleteImagesOptions instance
     */
    public BulkDeleteImagesOptions build() {
      return new BulkDeleteImagesOptions(this);
    }

    /**
     * Adds an bulkDelete to bulkDelete.
     *
     * @param bulkDelete the new bulkDelete
     * @return the BulkDeleteImagesOptions builder
     */
    public Builder addBulkDelete(String bulkDelete) {
      com.ibm.cloud.sdk.core.util.Validator.notNull(bulkDelete,
        "bulkDelete cannot be null");
      if (this.bulkDelete == null) {
        this.bulkDelete = new ArrayList<String>();
      }
      this.bulkDelete.add(bulkDelete);
      return this;
    }

    /**
     * Set the bulkDelete.
     * Existing bulkDelete will be replaced.
     *
     * @param bulkDelete the bulkDelete
     * @return the BulkDeleteImagesOptions builder
     */
    public Builder bulkDelete(List<String> bulkDelete) {
      this.bulkDelete = bulkDelete;
      return this;
    }
  }

  protected BulkDeleteImagesOptions() { }

  protected BulkDeleteImagesOptions(Builder builder) {
    com.ibm.cloud.sdk.core.util.Validator.notNull(builder.bulkDelete,
      "bulkDelete cannot be null");
    bulkDelete = builder.bulkDelete;
  }

  /**
   * New builder.
   *
   * @return a BulkDeleteImagesOptions builder
   */
  public Builder newBuilder() {
    return new Builder(this);
  }

  /**
   * Gets the bulkDelete.
   *
   * The full IBM Cloud registry path to the images that you want to delete, including its digest. All tags for the
   * supplied digest are removed.
   *
   * @return the bulkDelete
   */
  public List<String> bulkDelete() {
    return bulkDelete;
  }
}

