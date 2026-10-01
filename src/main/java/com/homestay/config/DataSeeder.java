package com.homestay.config;

import com.homestay.entity.Room;
import com.homestay.entity.User;
import com.homestay.repository.RoomRepository;
import com.homestay.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class DataSeeder implements CommandLineRunner {

    private final RoomRepository roomRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public DataSeeder(RoomRepository roomRepository, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.roomRepository = roomRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {
        if (roomRepository.count() == 0) {
            roomRepository.save(Room.builder()
                .title("Villa Sunset View")
                .city("Đà Lạt")
                .pricePerNight(new BigDecimal("1200000"))
                .maxGuests(4)
                .bedrooms(2)
                .bathrooms(2)
                .rating(5)
                .description("Homestay sang trọng với view núi và hồ nước, không gian rất phù hợp cho gia đình.")
                .imageUrl("https://images.unsplash.com/photo-1505693416388-ac5ce068fe85?auto=format&fit=crop&w=900&q=80")
                .active(true)
                .build());

            roomRepository.save(Room.builder()
                .title("Cozy Pine House")
                .city("Nha Trang")
                .pricePerNight(new BigDecimal("950000"))
                .maxGuests(3)
                .bedrooms(1)
                .bathrooms(1)
                .rating(4)
                .description("Căn nhà ấm cúng gần biển, thích hợp cho du khách muốn nghỉ dưỡng thoải mái.")
                .imageUrl("https://images.unsplash.com/photo-1494526585095-c41746248156?auto=format&fit=crop&w=900&q=80")
                .active(true)
                .build());

            roomRepository.save(Room.builder()
                .title("Mountain Retreat")
                .city("Sapa")
                .pricePerNight(new BigDecimal("1400000"))
                .maxGuests(2)
                .bedrooms(1)
                .bathrooms(1)
                .rating(5)
                .description("Không gian yên bình trên cao nguyên, rất lý tưởng cho cặp đôi và nghỉ dưỡng thư giãn.")
                .imageUrl("https://images.unsplash.com/photo-1445019980597-93fa8acb246c?auto=format&fit=crop&w=900&q=80")
                .active(true)
                .build());

            roomRepository.save(Room.builder()
                .title("Garden Studio")
                .city("Hội An")
                .pricePerNight(new BigDecimal("850000"))
                .maxGuests(2)
                .bedrooms(1)
                .bathrooms(1)
                .rating(4)
                .description("Studio xanh mát, gần phố cổ, giúp bạn trải nghiệm chất lượng nghỉ dưỡng tuyệt vời.")
                .imageUrl("https://images.unsplash.com/photo-1484154218962-a197022b5858?auto=format&fit=crop&w=900&q=80")
                .active(true)
                .build());
        }

        if (userRepository.findByUsername("admin").isEmpty()) {
            userRepository.save(User.builder()
                .username("admin")
                .email("admin@staynest.vn")
                .password(passwordEncoder.encode("admin123"))
                .role("ADMIN")
                .build());
        }
    }
}
