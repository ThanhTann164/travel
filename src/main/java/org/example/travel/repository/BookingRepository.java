package org.example.travel.repository;

import org.example.travel.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long>{
    @Query("SELECT b FROM Booking b WHERE b.user.userID = :userId")
    List<Booking> findByUserID(Long userId);

    @Query("SELECT b FROM Booking b WHERE b.user.userID = :userID")
    List<Booking> findByUserIDAndStatusTrue(Long userID);

    List<Booking> findAll();

    Booking save(Booking booking);

//    existsByTourIdAndUserId
    boolean existsByTourTourIDAndUserUserID(Long tourId, Long userID);
    //getTotalRevenue with enterpriseId
    @Query("SELECT SUM(b.totalPrice) FROM Booking b WHERE b.tour.enterprise.enterpriseID = :enterpriseID")
    Long getTotalRevenue(Long enterpriseID);

    // getTotalRevenueThisYear with enterpriseId
    @Query("SELECT SUM(b.totalPrice) FROM Booking b WHERE YEAR(b.createdAt) = YEAR(CURRENT_DATE) AND b.tour.enterprise.enterpriseID = :enterpriseID")
    Long getTotalRevenueThisYear(Long enterpriseID);

    // getTotalRevenueThisMonth with enterpriseId
    @Query("SELECT SUM(b.totalPrice) FROM Booking b WHERE MONTH(b.createdAt) = MONTH(CURRENT_DATE) AND b.tour.enterprise.enterpriseID = :enterpriseID")
    Long getTotalRevenueThisMonth(Long enterpriseID);


    // getTotalRevenueToday with enterpriseId
    @Query("SELECT SUM(b.totalPrice) FROM Booking b WHERE DATE(b.createdAt) = CURRENT_DATE AND b.tour.enterprise.enterpriseID = :enterpriseID")
    Long getTotalRevenueToday(Long enterpriseID);

    // getListRevenueJanuaryToDecember
    @Query("SELECT SUM(b.totalPrice) FROM Booking b WHERE MONTH(b.createdAt) = :month AND b.tour.enterprise.enterpriseID = :enterpriseID")
    Long getListRevenueJanuaryToDecember(int month, Long enterpriseID);

    // getBookingCountJanuaryToDecember
    @Query("SELECT COUNT(b) FROM Booking b WHERE MONTH(b.createdAt) = :month AND b.tour.enterprise.enterpriseID = :enterpriseID")
    Long getBookingCountJanuaryToDecember(int month, Long enterpriseID);

    // getRecentBookings
    @Query("SELECT b FROM Booking b WHERE b.tour.enterprise.enterpriseID = :enterpriseID ORDER BY b.createdAt DESC LIMIT 7")
    List<Booking> getRecentBookings(Long enterpriseID);

    // findByEnterpriseID
    @Query("SELECT b FROM Booking b WHERE b.tour.enterprise.enterpriseID = :enterpriseID")
    List<Booking> findByEnterpriseID(Long enterpriseID);

    // getTotalBookings with enterpriseId
    @Query("SELECT COUNT(b) FROM Booking b WHERE b.tour.enterprise.enterpriseID = :enterpriseID")
    Long getTotalBookings(Long enterpriseID);

    // findByUserIDAndStatus
    @Query("SELECT b FROM Booking b WHERE b.user.userID = :userID AND b.status = :status")
    List<Booking> findByUserIDAndStatus(Long userID, String status);

    // getTotalBookingsAdmin
    @Query("SELECT COUNT(b) FROM Booking b")
    Long getTotalBookingsAdmin();

    // getTotalRevenueAdmin
    @Query("SELECT SUM(b.totalPrice) FROM Booking b")
    Long getTotalRevenueAdmin();

    // getTotalRevenueThisYearAdmin
    @Query("SELECT SUM(b.totalPrice) FROM Booking b WHERE YEAR(b.createdAt) = YEAR(CURRENT_DATE)")
    Long getTotalRevenueThisYearAdmin();

    // getTotalRevenueThisMonthAdmin
    @Query("SELECT SUM(b.totalPrice) FROM Booking b WHERE MONTH(b.createdAt) = MONTH(CURRENT_DATE)")
    Long getTotalRevenueThisMonthAdmin();

    // getTotalRevenueTodayAdmin
    @Query("SELECT SUM(b.totalPrice) FROM Booking b WHERE DATE(b.createdAt) = CURRENT_DATE")
    Long getTotalRevenueTodayAdmin();

    // getListRevenueJanuaryToDecemberAdmin
    @Query("SELECT SUM(b.totalPrice) FROM Booking b WHERE MONTH(b.createdAt) = :month")
    Long getListRevenueJanuaryToDecemberAdmin(int month);

    // getBookingCountJanuaryToDecemberAdmin
    @Query("SELECT COUNT(b) FROM Booking b WHERE MONTH(b.createdAt) = :month")
    Long getBookingCountJanuaryToDecemberAdmin(int month);

    // getListVisitorJanuaryToDecemberAdmin
    @Query("SELECT SUM(t.numberViewed) FROM Tour t WHERE MONTH(t.createdAt) = :month")
    Long getListVisitorJanuaryToDecemberAdmin(int month);

    // getRecentBookingsAdmin
    @Query("SELECT b FROM Booking b ORDER BY b.createdAt DESC LIMIT 7")
    List<Booking> getRecentBookingsAdmin();
}
