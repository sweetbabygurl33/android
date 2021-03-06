
package com.bitlove.fetlife.model.pojos.fetlife.json;

import android.support.annotation.NonNull;
import android.util.Log;

import com.bitlove.fetlife.model.pojos.fetlife.dbjson.Member;
import com.bitlove.fetlife.util.DateUtil;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.clustering.ClusterItem;

import java.text.SimpleDateFormat;

public class Event implements Comparable<Event>, ClusterItem {

    @JsonProperty("address")
    private String address;

    @JsonProperty("content_type")
    private String contentType;

    @JsonProperty("created_at")
    private String createdAt;

    @JsonProperty("description")
    private String description;

    @JsonProperty("end_date_time")
    private String endDateTime;

    @JsonProperty("id")
    private String id;

    @JsonProperty("location")
    private String location;

    @JsonProperty("member")
    private Member member;

    @JsonProperty("name")
    private String name;

    @JsonProperty("start_date_time")
    private String startDateTime;

    @JsonProperty("tagline")
    private String tagline;

    @JsonProperty("url")
    private String url;

    @JsonProperty("latitude")
    private double latitude;

    @JsonProperty("longitude")
    private double longitude;

    @JsonProperty("distance")
    private double distance;

    @JsonProperty("cost")
    private String cost;

    @JsonProperty("dress_code")
    private String dressCode;


    private long roughtDate;


    @JsonProperty("address")
    public String getAddress() {
        return address;
    }

    @JsonProperty("address")
    public void setAddress(String address) {
        this.address = address;
    }

    @JsonProperty("content_type")
    public String getContentType() {
        return contentType;
    }

    @JsonProperty("content_type")
    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    @JsonProperty("cost")
    public String getCost() {
        return cost;
    }

    @JsonProperty("cost")
    public void setCost(String cost) {
        this.cost = cost;
    }

    @JsonProperty("created_at")
    public String getCreatedAt() {
        return createdAt;
    }

    @JsonProperty("created_at")
    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    @JsonProperty("description")
    public String getDescription() {
        return description;
    }

    @JsonProperty("description")
    public void setDescription(String description) {
        this.description = description;
    }

    @JsonProperty("dress_code")
    public String getDressCode() {
        return dressCode;
    }

    @JsonProperty("dress_code")
    public void setDressCode(String dressCode) {
        this.dressCode = dressCode;
    }

    @JsonProperty("end_date_time")
    public String getEndDateTime() {
        return endDateTime;
    }

    @JsonProperty("end_date_time")
    public void setEndDateTime(String endDateTime) {
        this.endDateTime = endDateTime;
    }

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    @JsonProperty("location")
    public String getLocation() {
        return location;
    }

    @JsonProperty("location")
    public void setLocation(String location) {
        this.location = location;
    }

    @JsonProperty("member")
    public Member getMember() {
        return member;
    }

    @JsonProperty("member")
    public void setMember(Member member) {
        this.member = member;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("start_date_time")
    public String getStartDateTime() {
        return startDateTime;
    }

    @JsonProperty("start_date_time")
    public void setStartDateTime(String startDateTime) {
        this.startDateTime = startDateTime;
        if (startDateTime != null) {
            roughtDate = DateUtil.addRoughTimeOffset(DateUtil.parseDate(startDateTime,true), longitude);
        }
    }

    @JsonProperty("tagline")
    public String getTagline() {
        return tagline;
    }

    @JsonProperty("tagline")
    public void setTagline(String tagline) {
        this.tagline = tagline;
    }

    @JsonProperty("url")
    public String getUrl() {
        return url;
    }

    @JsonProperty("url")
    public void setUrl(String url) {
        this.url = url;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    @Override
    public LatLng getPosition() {
        return new LatLng(getLatitude(),getLongitude());
    }

    public void setPosition(LatLng position) {
        setLatitude(position.latitude);
        setLongitude(position.longitude);
    }

    @Override
    public String getTitle() {
        return name != null ? name : "";
    }

    @Override
    public String getSnippet() {
        String time = getStartDateTime();
        String snippet = time != null ? SimpleDateFormat.getDateTimeInstance().format(DateUtil.parseDate(time,true)) : "";
        return snippet;
    }

    public long getRoughtDate() {
        return roughtDate;
    }

    @Override
    public int hashCode() {
        return (int) roughtDate;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Event)) {
            return false;
        }

        Event otherEvent = (Event) obj;
        return id.equals(otherEvent.id);
    }

    @Override
    public int compareTo(@NonNull Event o) {
        long diff = roughtDate - o.roughtDate;
        Log.d("COMPARE", roughtDate + " ? " + o.roughtDate + " = " + diff);
        if (diff == 0) return 0;
        if (diff < 0) return -1;
        return 1;
    }

}
