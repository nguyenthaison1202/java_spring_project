package com.mockproject.group3.repository;

import java.util.List;
import java.time.LocalDateTime;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mockproject.group3.dto.response.course.CourseWithRatingRes;
import com.mockproject.group3.dto.response.instructor.EarningInstructorRes;
import com.mockproject.group3.enums.Status;
import com.mockproject.group3.model.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course, Integer> {
        @Query("SELECT new com.mockproject.group3.dto.response.course.CourseWithRatingRes(c, COALESCE(AVG(f.rating), 0.0) as avarageRating) "
                        +
                        "FROM Course c " +
                        "LEFT JOIN c.feedbacks f " +
                        "WHERE ( c.title LIKE %:keyword% OR c.instructor.user.full_name LIKE%:keyword%) AND c.category.id=:categoryId AND c.status='APPROVED' "
                        +
                        "GROUP BY c.id " +
                        "ORDER BY avarageRating DESC")
        Page<CourseWithRatingRes> findByKeywordWithCategoryOnlyActivate(@Param("keyword") String keyword,
                        @Param("categoryId") int categoryId, Pageable pageable);

        @Query("SELECT new com.mockproject.group3.dto.response.course.CourseWithRatingRes(c, COALESCE(AVG(f.rating), 0.0) as avarageRating) "
                        +
                        "FROM Course c " +
                        "LEFT JOIN c.feedbacks f " +
                        "WHERE ( c.title LIKE %:keyword% OR c.instructor.user.full_name LIKE %:keyword% ) AND c.status = 'APPROVED' "
                        +
                        "GROUP BY c.id " +
                        "ORDER BY avarageRating DESC")
        Page<CourseWithRatingRes> findByKeywordOnlyActivate(@Param("keyword") String keyword, Pageable pageable);

        @Query("SELECT c FROM Course c WHERE c.title LIKE %:keyword% AND c.instructor.id = %:instructor_id%")
        Page<Course> findCourseByInstructor(@Param("keyword") String keyword, @Param("instructor_id") int instructorId,
                        Pageable pageable);
}
