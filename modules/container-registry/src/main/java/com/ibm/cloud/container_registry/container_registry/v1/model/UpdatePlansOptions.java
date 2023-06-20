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
 * The updatePlans options.
 */
public class UpdatePlansOptions extends GenericModel {

  protected String plan;

  /**
   * Builder.
   */
  public static class Builder {
    private String plan;

    /**
     * Instantiates a new Builder from an existing UpdatePlansOptions instance.
     *
     * @param updatePlansOptions the instance to initialize the Builder with
     */
    private Builder(UpdatePlansOptions updatePlansOptions) {
      this.plan = updatePlansOptions.plan;
    }

    /**
     * Instantiates a new builder.
     */
    public Builder() {
    }

    /**
     * Builds a UpdatePlansOptions.
     *
     * @return the new UpdatePlansOptions instance
     */
    public UpdatePlansOptions build() {
      return new UpdatePlansOptions(this);
    }

    /**
     * Set the plan.
     *
     * @param plan the plan
     * @return the UpdatePlansOptions builder
     */
    public Builder plan(String plan) {
      this.plan = plan;
      return this;
    }

    /**
     * Set the plan.
     *
     * @param plan the plan
     * @return the UpdatePlansOptions builder
     */
    public Builder plan(Plan plan) {
      this.plan = plan.plan();
      return this;
    }
  }

  protected UpdatePlansOptions() { }

  protected UpdatePlansOptions(Builder builder) {
    plan = builder.plan;
  }

  /**
   * New builder.
   *
   * @return a UpdatePlansOptions builder
   */
  public Builder newBuilder() {
    return new Builder(this);
  }

  /**
   * Gets the plan.
   *
   * @return the plan
   */
  public String plan() {
    return plan;
  }
}

