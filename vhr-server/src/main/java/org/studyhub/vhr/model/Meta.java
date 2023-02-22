package org.studyhub.vhr.model;

public class Meta {

    private Boolean keepAlive;

    private Boolean requireAuth;

    public Meta() {
    }

    public Meta(Boolean keepAlive, Boolean requireAuth) {
        this.keepAlive = keepAlive;
        this.requireAuth = requireAuth;
    }

    public Boolean getKeepAlive() {
        return keepAlive;
    }

    public void setKeepAlive(Boolean keepAlive) {
        this.keepAlive = keepAlive;
    }

    public Boolean getRequireAuth() {
        return requireAuth;
    }

    public void setRequireAuth(Boolean requireAuth) {
        this.requireAuth = requireAuth;
    }
}
