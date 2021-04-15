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
 * The deleteNamespace options.
 */
public class DeleteNamespaceOptions extends GenericModel {

  protected String name;

  /**
   * Builder.
   */
  public static class Builder {
    private String name;

    private Builder(DeleteNamespaceOptions deleteNamespaceOptions) {
      this.name = deleteNamespaceOptions.name;
    }

    /**
     * Instantiates a new builder.
     */
    public Builder() {
    }

    /**
     * Instantiates a new builder with required properties.
     *
     * @param name the name
     */
    public Builder(String name) {
      this.name = name;
    }

    /**
     * Builds a DeleteNamespaceOptions.
     *
     * @return the new DeleteNamespaceOptions instance
     */
    public DeleteNamespaceOptions build() {
      return new DeleteNamespaceOptions(this);
    }

    /**
     * Set the name.
     *
     * @param name the name
     * @return the DeleteNamespaceOptions builder
     */
    public Builder name(String name) {
      this.name = name;
      return this;
    }
  }

  protected DeleteNamespaceOptions(Builder builder) {
    com.ibm.cloud.sdk.core.util.Validator.notEmpty(builder.name,
      "name cannot be empty");
    name = builder.name;
  }

  /**
   * New builder.
   *
   * @return a DeleteNamespaceOptions builder
   */
  public Builder newBuilder() {
    return new Builder(this);
  }

  /**
   * Gets the name.
   *
   * The name of the namespace that you want to delete.
   *
   * @return the name
   */
  public String name() {
    return name;
  }
}

