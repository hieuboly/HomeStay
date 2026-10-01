package com.homestay.service;

import com.homestay.entity.Room;
import com.homestay.repository.RoomRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class RoomService {

    private final RoomRepository roomRepository;

    public RoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    public List<Room> getFeaturedRooms() {
        List<Room> rooms = roomRepository.findByActiveTrue();
        if (!rooms.isEmpty()) {
            return rooms.stream().limit(4).toList();
        }

        return List.of(
            Room.builder()
                .title("Villa Sunset View")
                .city("Đà Lạt")
                .pricePerNight(new BigDecimal("1200000"))
                .maxGuests(4)
                .bedrooms(2)
                .bathrooms(2)
                .rating(5)
                .description("Homestay view sông với phong cách hiện đại, thoáng mát, lý tưởng cho du lịch gia đình.")
                .imageUrl("https://images.unsplash.com/photo-1505693416388-ac5ce068fe85?auto=format&fit=crop&w=900&q=80")
                .active(true)
                .build(),
            Room.builder()
                .title("Cozy Pine House")
                .city("Nha Trang")
                .pricePerNight(new BigDecimal("950000"))
                .maxGuests(3)
                .bedrooms(1)
                .bathrooms(1)
                .rating(4)
                .description("Căn nhà nhỏ ấm cúng gần biển, thích hợp cho cặp đôi hoặc gia đình nhỏ.")
                .imageUrl("https://images.unsplash.com/photo-1494526585095-c41746248156?auto=format&fit=crop&w=900&q=80")
                .active(true)
                .build(),
            Room.builder()
                .title("Mountain Retreat")
                .city("Sapa")
                .pricePerNight(new BigDecimal("1400000"))
                .maxGuests(2)
                .bedrooms(1)
                .bathrooms(1)
                .rating(5)
                .description("Không gian yên bình giữa núi rừng, mang cảm giác thư thái hoàn toàn.")
                .imageUrl("https://images.unsplash.com/photo-1445019980597-93fa8acb246c?auto=format&fit=crop&w=900&q=80")
                .active(true)
                .build(),
            Room.builder()
                .title("Garden Studio")
                .city("Hội An")
                .pricePerNight(new BigDecimal("850000"))
                .maxGuests(2)
                .bedrooms(1)
                .bathrooms(1)
                .rating(4)
                .description("Phòng studio xanh mát, gần phố cổ, rất phù hợp cho kỳ nghỉ ngắn.")
                .imageUrl("https://images.unsplash.com/photo-1484154218962-a197022b5858?auto=format&fit=crop&w=900&q=80")
                .active(true)
                .build()
        );
    }

    public List<Room> getAllRooms() {
        return roomRepository.findByActiveTrue();
    }

    public Room getRoomById(Long id) {
        return roomRepository.findById(id).orElseThrow(() -> new RuntimeException("Room not found"));
    }

    public Room save(Room room) {
        return roomRepository.save(room);
    }
}
