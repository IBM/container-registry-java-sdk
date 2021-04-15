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
 * The assignNamespace options.
 */
public class AssignNamespaceOptions extends GenericModel {

  protected String xAuthResourceGroup;
  protected String name;

  /**
   * Builder.
   */
  public static class Builder {
    private String xAuthResourceGroup;
    private String name;

    private Builder(AssignNamespaceOptions assignNamespaceOptions) {
      this.xAuthResourceGroup = assignNamespaceOptions.xAuthResourceGroup;
      this.name = assignNamespaceOptions.name;
    }

    /**
     * Instantiates a new builder.
     */
    public Builder() {
    }

    /**
     * Instantiates a new builder with required properties.
     *
     * @param xAuthResourceGroup the xAuthResourceGroup
     * @param name the name
     */
    public Builder(String xAuthResourceGroup, String name) {
      this.xAuthResourceGroup = xAuthResourceGroup;
      this.name = name;
    }

    /**
     * Builds a AssignNamespaceOptions.
     *
     * @return the new AssignNamespaceOptions instance
     */
    public AssignNamespaceOptions build() {
      return new AssignNamespaceOptions(this);
    }

    /**
     * Set the xAuthResourceGroup.
     *
     * @param xAuthResourceGroup the xAuthResourceGroup
     * @return the AssignNamespaceOptions builder
     */
    public Builder xAuthResourceGroup(String xAuthResourceGroup) {
      this.xAuthResourceGroup = xAuthResourceGroup;
      return this;
    }

    /**
     * Set the name.
     *
     * @param name the name
     * @return the AssignNamespaceOptions builder
     */
    public Builder name(String name) {
      this.name = name;
      return this;
    }
  }

  protected AssignNamespaceOptions(Builder builder) {
    com.ibm.cloud.sdk.core.util.Validator.notNull(builder.xAuthResourceGroup,
      "xAuthResourceGroup cannot be null");
    com.ibm.cloud.sdk.core.util.Validator.notEmpty(builder.name,
      "name cannot be empty");
    xAuthResourceGroup = builder.xAuthResourceGroup;
    name = builder.name;
  }

  /**
   * New builder.
   *
   * @return a AssignNamespaceOptions builder
   */
  public Builder newBuilder() {
    return new Builder(this);
  }

  /**
   * Gets the xAuthResourceGroup.
   *
   * The ID of the resource group that the namespace will be created within.
   *
   * @return the xAuthResourceGroup
   */
  public String xAuthResourceGroup() {
    return xAuthResourceGroup;
  }

  /**
   * Gets the name.
   *
   * The name of the namespace to be updated.
   *
   * @return the name
   */
  public String name() {
    return name;
  }
}

