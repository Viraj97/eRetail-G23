import java.time.LocalDateTime;

public class Review {
    private String reviewId;
    private String customerId;
    private String productId;
    private int rating;
    private String comment;
    private LocalDateTime timestamp;

    public Review(String reviewId, String customerId, String productId, int rating, String comment) {
        this.reviewId = reviewId;
        this.customerId = customerId;
        this.productId = productId;
        this.rating = rating;
        this.comment = comment;
        this.timestamp = LocalDateTime.now();
    }

    public void writeReview() {
        System.out.println("Review submitted:");
        System.out.println("Product ID: " + productId);
        System.out.println("Rating: " + rating + "/5");
        System.out.println("Comment: " + comment);
        System.out.println("Time: " + timestamp);
    }

    public void editReview(int newRating, String newComment) {
        this.rating = newRating;
        this.comment = newComment;
        this.timestamp = LocalDateTime.now();
        System.out.println("Review updated successfully.");
    }

    public String getReviewId() {
        return reviewId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getProductId() {
        return productId;
    }

    public int getRating() {
        return rating;
    }

    public String getComment() {
        return comment;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}
