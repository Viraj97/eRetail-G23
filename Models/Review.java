package Models;

import java.util.*;

public class Review {
    private final UUID reviewId;
    private final String customerId;
    private final String productId;
    private int rating; // 1-5
    private String comment;

    public Review(UUID reviewId, String customerId, String productId, int rating, String comment) {
        this.reviewId = reviewId;
        this.customerId = customerId;
        this.productId = productId;
        this.rating = rating;
        this.comment = comment;
    }

    public void writeReview() {
        System.out.println("Review submitted: " + comment + " Rating: " + rating);
        // You would save this in DB or in-memory list
    }

    public void editReview(int newRating, String newComment) {
        this.rating = newRating;
        this.comment = newComment;
        System.out.println("Review updated.");
    }

    public void deleteReview() {
        System.out.println("Review deleted.");
        // remove from storage
    }
}
