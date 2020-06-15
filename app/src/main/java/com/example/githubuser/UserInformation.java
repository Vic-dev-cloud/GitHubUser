package com.example.githubuser;

public class UserInformation {

    private String login;
    private String id;
    private String node_id;
    private String avatar_url;
    private String gravatar_id;
    private String url;
    private String html_url;
    private String followers_url;
    private String following_url;
    private String gists_url;
    private String starred_url;
    private String subscriptions_url;
    private String organizations_url;
    private String repos_url;
    private String events_url;
    private String received_events_url;
    private String type;
    private String site_admin;

    public String getLogin(){
        return login;
    }
    public String getAvatarUrl() {
        return avatar_url;
    }

    public boolean getSiteAdmin() {
        if (site_admin.equals("true")){
            return true;
        } else {
            return false;
        }
    }

    public String getID() {
        return id;
    }

    public String getNodeID() {
        return node_id;
    }

    public String getGravatarID() {
        return gravatar_id;
    }

    public String getUrl() {
        return url;
    }

    public String getHtmlUrl() {
        return html_url;
    }

    public String getFollowersUrl() {
        return followers_url;
    }

    public String getFollowingUrl() {
        return following_url;
    }

    public String getGistsUrl() {
        return gists_url;
    }

    public String getStarredUrl() {
        return starred_url;
    }

    public String getSubscriptionsUrl() {
        return subscriptions_url;
    }

    public String getOrganizationsUrl() {
        return organizations_url;
    }

    public String getReposUrl() {
        return repos_url;
    }

    public String getEventsUrl() {
        return events_url;
    }

    public String getReceivedEventsUrl() {
        return received_events_url;
    }

    public String getType() {
        return type;
    }
}
