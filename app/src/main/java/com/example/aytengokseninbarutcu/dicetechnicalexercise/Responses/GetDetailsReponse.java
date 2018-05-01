package com.example.aytengokseninbarutcu.dicetechnicalexercise.Responses;

import com.google.gson.annotations.SerializedName;

import java.util.EmptyStackException;
import java.util.List;

/**
 * Created by aytengokseninbarutcu on 1.05.2018.
 */

public class GetDetailsReponse {

    @SerializedName("count")
    public int count;

    @SerializedName("total")
    public int total;

    @SerializedName("_embedded")
    public Embedded embedded;

    @SerializedName("_links")
    public Link links;

    public static class Embedded {

        @SerializedName("tags")
        public List<Tag> tags;
    }

    public static class Tag {

        @SerializedName("appeared_at")
        public String AppearedAt;
        @SerializedName("created_at")
        public String CreatedAt;
        @SerializedName("quote_id")
        public String QuoteId;
        @SerializedName("tags")
        public List<String> tagNames;
        @SerializedName("updated_at")
        public String UpdatedAt;
        @SerializedName("value")
        public String Value;
        @SerializedName("_links")
        public Link linkList;
        @SerializedName("_embedded")
        public EmbeddedDetails embeddedDetails;

    }

    public static class Link {

        @SerializedName("self")
        public Href self;
        @SerializedName("prev")
        public Href prev;
        @SerializedName("next")
        public Href next;
        @SerializedName("first")
        public Href first;
        @SerializedName("last")
        public Href last;
    }

    public static class Href {
        @SerializedName("href")
        public String href;
    }

    public static class EmbeddedDetails {

        @SerializedName("author")
        public List<Author> authors;
        @SerializedName("source")
        public List<Source> sources;
    }

    public static class Author {

        @SerializedName("author_id")
        public String AuthorId;
        @SerializedName("bio")
        public Object Bio;
        @SerializedName("created_at")
        public String CreatedAt;
        @SerializedName("name")
        public String Name;
        @SerializedName("slug")
        public String Slug;
        @SerializedName("updated_at")
        public String UpdatedAt;
    }

    public static class Source {

        @SerializedName("created_at")
        public String CreatedAt;
        @SerializedName("filename")
        public Object Filename;
        @SerializedName("quote_source_id")
        public String QuoteSourceId;
        @SerializedName("remarks")
        public Object Remarks;
        @SerializedName("updated_at")
        public String UpdatedAt;
        @SerializedName("url")
        public String Url;
    }

}
