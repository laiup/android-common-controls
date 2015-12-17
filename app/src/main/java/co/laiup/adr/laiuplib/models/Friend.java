package co.laiup.adr.laiuplib.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Project android-common-controls
 * Created by Ha on 11/18/2015.
 */
public class Friend implements Parcelable {
    private String id;
    private String firstName;
    private String lastName;
    private String displayName;

    public Friend() {
    }

    public Friend(Parcel input) {
        id = input.readString();
        firstName = input.readString();
        lastName = input.readString();
        displayName = input.readString();
    }

    public Friend(String id, String firstName, String lastName, String displayName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.displayName = displayName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(firstName);
        dest.writeString(lastName);
        dest.writeString(displayName);
    }

    public static final Parcelable.Creator<Friend> CREATOR
            = new Creator<Friend>() {
        @Override
        public Friend createFromParcel(Parcel parcel) {
            return new Friend(parcel);
        }

        @Override
        public Friend[] newArray(int i) {
            return new Friend[i];
        }
    };

}
