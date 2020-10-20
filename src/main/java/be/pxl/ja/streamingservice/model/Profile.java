package be.pxl.ja.streamingservice.model;

import java.time.LocalDate;
import java.util.Deque;

public class Profile implements Comparable<Profile>{
    private String name;
    private LocalDate dateOfBirth;
    private String avatar;
    private Deque<Content> recentlyWatched;
    private Deque<Content> currentlyWatching;
    private Deque<Content> myList;


    public Profile(String name, String avatar) {
        this.name = name;
        this.avatar = avatar;
    }

    public Profile(String name) {
        this(name, "profile1");
    }

    public String getName() {
        return name;
    }

    public void startWatching(Content content) {
        if (!currentlyWatching.contains(content)) {
            currentlyWatching.addFirst(content);
        }
    }

    public void finishWatching(Content content) {
        currentlyWatching.remove(content);
        recentlyWatched.addFirst(content);
    }

    public void addToMyList(Content content) {
        if (!myList.contains(content)) {
            myList.add(content);
        }
    }

    public void removeFromMyList(Content content) {
        myList.remove(content);
    }

    public boolean isInMyList(Content content) {
        return myList.contains(content);
    }

    public boolean allowedToWatch(Content content) {
        return LocalDate.now().isAfter(dateOfBirth.plusYears(content.getMaturityRating().getMinimumAge()));
    }
    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getAvatar() {
        return avatar;
    }

    public Deque<Content> getRecentlyWatched() {
        return recentlyWatched;
    }

    public Deque<Content> getCurrentlyWatching() {
        return currentlyWatching;
    }

    public Deque<Content> getMyList() {
        return myList;
    }

    @Override
    public int compareTo(Profile other) {
        return this.name.compareTo(other.name);
    }
}
