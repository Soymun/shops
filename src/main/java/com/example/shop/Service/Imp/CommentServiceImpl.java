package com.example.shop.Service.Imp;

import com.example.shop.DTO.Comment.CommentCreateDto;
import com.example.shop.DTO.Comment.CommentDTO;
import com.example.shop.DTO.Comment.CommentUpdateDto;
import com.example.shop.Entity.Comment;
import com.example.shop.Entity.Comment_;
import com.example.shop.Exception.NoFoundException;
import com.example.shop.Mappers.CommentMapper;
import com.example.shop.Repository.CommentRepository;
import com.example.shop.Service.CommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

    private final CommentMapper commentMapper;

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public void createComment(CommentCreateDto commentCreateDto) {
        log.info("Сохранение коментария");
        Comment comment = commentMapper.commentCreateDtoToComment(commentCreateDto);
        comment.setLocalDateTime(LocalDateTime.now());
        commentRepository.save(comment);
    }

    @Override
    @Transactional
    public void deleteCommentById(Long id) {
        log.info("Удаление коментария с id {}", id);
        commentRepository.deleteById(id);
    }

    @Override
    public CommentDTO getCommentById(Long id) {
        log.info("Выдача коментария по id {}", id);
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<CommentDTO> cq = cb.createQuery(CommentDTO.class);
        Root<Comment> root = cq.from(Comment.class);

        cq.where(cb.equal(root.get(Comment_.id), id));

        cq.orderBy(cb.desc(root.get(Comment_.localDateTime)));

        cq.multiselect(
                root.get(Comment_.id),
                root.get(Comment_.productId),
                root.get(Comment_.userId),
                root.get(Comment_.localDateTime),
                root.get(Comment_.comment),
                root.get(Comment_.rating)
        );
        return entityManager.createQuery(cq).getSingleResult();
    }

    @Override
    public List<CommentDTO> getCommentByProductId(Long id) {
        log.info("Выдача коментарий по продукту с id {}", id);
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<CommentDTO> cq = cb.createQuery(CommentDTO.class);
        Root<Comment> root = cq.from(Comment.class);

        cq.where(cb.equal(root.get(Comment_.productId), id));

        cq.orderBy(cb.desc(root.get(Comment_.localDateTime)));

        cq.multiselect(
                root.get(Comment_.id),
                root.get(Comment_.productId),
                root.get(Comment_.userId),
                root.get(Comment_.localDateTime),
                root.get(Comment_.comment),
                root.get(Comment_.rating)
        );
        return entityManager.createQuery(cq).getResultList();
    }

    @Override
    public List<CommentDTO> getCommentByUserId(Long id) {
        log.info("Выдача коментариев по пользевателю с id {}", id);
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<CommentDTO> cq = cb.createQuery(CommentDTO.class);
        Root<Comment> root = cq.from(Comment.class);

        cq.where(cb.equal(root.get(Comment_.userId), id));

        cq.multiselect(
                root.get(Comment_.id),
                root.get(Comment_.productId),
                root.get(Comment_.userId),
                root.get(Comment_.localDateTime),
                root.get(Comment_.comment),
                root.get(Comment_.rating)
        );
        return entityManager.createQuery(cq).getResultList();
    }

    @Override
    public CommentDTO updateComment(CommentUpdateDto commentUpdateDto) {
        log.info("Изменеие коментария");
        Comment comment = commentRepository.findById(commentUpdateDto.getId()).orElseThrow(() -> {
            throw new NoFoundException("Коментарий не найден");
        });
        if(commentUpdateDto.getComment() != null){
            comment.setComment(commentUpdateDto.getComment());
        }
        if(commentUpdateDto.getRating() != null){
            comment.setRating(commentUpdateDto.getRating());
        }
        return commentMapper.commentToCommentDto(commentRepository.save(comment));
    }
}
