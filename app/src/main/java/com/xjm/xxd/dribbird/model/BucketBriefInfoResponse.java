package com.xjm.xxd.dribbird.model;

import com.google.gson.annotations.SerializedName;

/**
 * @author : xiaoxiaoda
 *         date: 16-12-7
 *         email: daque@hustunique.com
 */
public class BucketBriefInfoResponse {

    /**
     * id : 2754
     * name : Great Marks
     * description : Collecting superb brand marks from the <a href="https://dribbble.com">Dribbble verse</a>.
     * shots_count : 251
     * created_at : 2011-05-20T21:05:55Z
     * updated_at : 2014-02-21T16:37:12Z
     */

    private int id;
    private String name;
    private String description;
    @SerializedName("shots_count")
    private int shotsCount;
    @SerializedName("created_at")
    private String createdAt;
    @SerializedName("updated_at")
    private String updatedAt;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getShotsCount() {
        return shotsCount;
    }

    public void setShotsCount(int shotsCount) {
        this.shotsCount = shotsCount;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }
}
