package co.laiup.adr.laiuplib.networks;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import co.laiup.adr.laiuplib.constants.Constants;
import co.laiup.adr.laiuplib.models.Story;

/**
 * Created by Ha on 7/15/2015.
 */
public class JSONParser {
//
//    /*
//     * @description: constant represent property names of JSON response
//     * @for: #YoutubeVideo
//     */
//    public static final String YOUTUBE_SEARCH_ITEMS = "items";
//    public static final String YOUTUBE_SEARCH_ID = "id";
//    public static final String YOUTUBE_SEARCH_VIDEO_ID = "videoId";
//    public static final String YOUTUBE_SEARCH_SNIPPET = "snippet";
//    public static final String YOUTUBE_SEARCH_TITLE = "title";
//    public static final String YOUTUBE_SEARCH_DESCRIPTION = "description";
//    public static final String YOUTUBE_SEARCH_PUBLISHED_AT = "publishedAt";
//    public static final String YOUTUBE_SEARCH_THUMBNAIL = "thumbnails";
//    public static final String YOUTUBE_SEARCH_THUMBNAIL_DEFAULT = "default";
//    public static final String YOUTUBE_SEARCH_THUMBNAIL_URL = "url";
//    public static final String YOUTUBE_SEARCH_CHANNEL_ID = "channelId";
//    public static final String YOUTUBE_SEARCH_CHANNEL_TITLE = "channelTitle";
//
//    /*
//     * @function: parse JSON result of API #API_YOUTUBE_SEARCH to #ArrayList #YoutubeVideo
//     * @input: JSONArray
//     * @output: ArrayList<YoutubeVideo>
//     */
//    public static ArrayList<Story> storyParser(JSONObject data) {
//        ArrayList<Story> videos = new ArrayList<>();
//        // put in try-catch cuz we can get parse error while get json value
//        try {
//            // get json array
//            if (data.has(YOUTUBE_SEARCH_ITEMS) && !data.isNull(YOUTUBE_SEARCH_ITEMS)) {
//                JSONArray items = data.getJSONArray(YOUTUBE_SEARCH_ITEMS);
//
//                // #note: any value will be used more than 2 times -> assign it to a variable
//                int size = items.length();
//
//                if (size > 0) {
//                    // #variables to store properties' value
//                    String id, title, description, publishedAt, thumbnailDefault, channelId, channelTitle;
//
//                    for (int i = 0; i < size; i++) {
//                        // #set default value
//                        id = Constants.NA;
//                        title = Constants.NA;
//                        description = Constants.NA;
//                        publishedAt = Constants.NA;
//                        thumbnailDefault = Constants.NA;
//                        channelId = Constants.NA;
//                        channelTitle = Constants.NA;
//
//                        // #create new object
//                        Story video = new Story();
//                        JSONObject item = items.getJSONObject(i);
//                        if (item.has(YOUTUBE_SEARCH_ID) && !item.isNull(YOUTUBE_SEARCH_ID)) {
//                            JSONObject jId = item.getJSONObject(YOUTUBE_SEARCH_ID);
//                            if (jId.has(YOUTUBE_SEARCH_VIDEO_ID) && !jId.isNull(YOUTUBE_SEARCH_VIDEO_ID)) {
//                                id = jId.getString(YOUTUBE_SEARCH_VIDEO_ID);
//                            }
//                        }
//                        if (item.has(YOUTUBE_SEARCH_SNIPPET) && !item.isNull(YOUTUBE_SEARCH_SNIPPET)) {
//                            JSONObject jSnippet = item.getJSONObject(YOUTUBE_SEARCH_SNIPPET);
//                            // #get video title
//                            if (jSnippet.has(YOUTUBE_SEARCH_TITLE) && !jSnippet.isNull(YOUTUBE_SEARCH_TITLE)) {
//                                title = jSnippet.getString(YOUTUBE_SEARCH_TITLE);
//                            }
//
//                            // #get video description
//                            if (jSnippet.has(YOUTUBE_SEARCH_DESCRIPTION) && !jSnippet.isNull(YOUTUBE_SEARCH_DESCRIPTION)) {
//                                description = jSnippet.getString(YOUTUBE_SEARCH_DESCRIPTION);
//                            }
//
//                            // #get video published at
//                            if (jSnippet.has(YOUTUBE_SEARCH_PUBLISHED_AT) && !jSnippet.isNull(YOUTUBE_SEARCH_PUBLISHED_AT)) {
//                                publishedAt = jSnippet.getString(YOUTUBE_SEARCH_PUBLISHED_AT);
//                            }
//
//                            // #get video thumbnail
//                            if (jSnippet.has(YOUTUBE_SEARCH_THUMBNAIL) && !jSnippet.isNull(YOUTUBE_SEARCH_THUMBNAIL)) {
//                                JSONObject jThumbnails = jSnippet.getJSONObject(YOUTUBE_SEARCH_THUMBNAIL);
//                                // #get thumbnail default
//                                if (jThumbnails.has(YOUTUBE_SEARCH_THUMBNAIL_DEFAULT) && !jThumbnails.isNull(YOUTUBE_SEARCH_THUMBNAIL_DEFAULT)) {
//                                    JSONObject jDefault = jThumbnails.getJSONObject(YOUTUBE_SEARCH_THUMBNAIL_DEFAULT);
//                                    // #get thumbnail default url
//                                    if (jDefault.has(YOUTUBE_SEARCH_THUMBNAIL_URL) && !jDefault.isNull(YOUTUBE_SEARCH_THUMBNAIL_URL)) {
//                                        thumbnailDefault = jDefault.getString(YOUTUBE_SEARCH_THUMBNAIL_URL);
//                                    }
//                                }
//                            }
//
//                            // #get channel id
//                            if (jSnippet.has(YOUTUBE_SEARCH_CHANNEL_ID) && !jSnippet.isNull(YOUTUBE_SEARCH_CHANNEL_ID)) {
//                                channelId = jSnippet.getString(YOUTUBE_SEARCH_CHANNEL_ID);
//                            }
//
//                            // #get channel title
//                            if (jSnippet.has(YOUTUBE_SEARCH_CHANNEL_TITLE) && !jSnippet.isNull(YOUTUBE_SEARCH_CHANNEL_TITLE)) {
//                                channelTitle = jSnippet.getString(YOUTUBE_SEARCH_CHANNEL_TITLE);
//                            }
//                        }
//
//                        // #check condition to add video in array list or not
//                        if (!id.equals(Constants.NA)) {
//                            video.setId(id);
//                            video.setTitle(title);
//                            video.setDescription(description);
//                            video.setPublishedAt(publishedAt);
//                            video.setThumbnailDefault(thumbnailDefault);
//                            video.setChannelId(channelId);
//                            video.setChannelTitle(channelTitle);
//                            videos.add(video);
//                        }
//                    }
//                }
//            }
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        return videos;
//    }

}
