package com.homestay.service;

import com.homestay.entity.Booking;
import com.homestay.entity.Room;
import com.homestay.entity.User;
import com.homestay.repository.BookingRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class BookingService {

    private final BookingRepository bookingRepository;

    public BookingService(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    public Booking createBooking(Room room, User user, LocalDate checkInDate, LocalDate checkOutDate,
                                 Integer guestCount, String customerName, String customerPhone, String notes) {
        long nights = ChronoUnit.DAYS.between(checkInDate, checkOutDate);
        if (nights <= 0) {
            throw new IllegalArgumentException("Ngày trả phòng phải sau ngày nhận phòng.");
        }

        BigDecimal totalPrice = room.getPricePerNight().multiply(BigDecimal.valueOf(nights));

        Booking booking = Booking.builder()
            .room(room)
            .user(user)
            .checkInDate(checkInDate)
            .checkOutDate(checkOutDate)
            .guestCount(guestCount)
            .totalPrice(totalPrice)
            .status("PENDING")
            .customerName(customerName)
            .customerPhone(customerPhone)
            .notes(notes)
            .build();

        return bookingRepository.save(booking);
    }

    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }
}
