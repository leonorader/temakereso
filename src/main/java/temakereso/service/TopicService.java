package temakereso.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import temakereso.entity.Student;
import temakereso.entity.Topic;
import temakereso.helper.*;

import java.util.List;
import java.util.Set;

public interface TopicService {

    /**
     * Returns a topic by id
     *
     * @param id
     * @return topic with the given id
     */
    TopicDto getOneById(Long id);

    /**
     * Returns a page of topics filtered by parameters
     *
     * @param pageable
     * @param filters
     * @return a page of topic dtos
     */
    Page<TopicListerDto> getFilteredOnesByPage(TopicFilters filters, Pageable pageable);

    /**
     * Creates a new topic
     *
     * @param supervisorId id of the supervisor
     * @param topic        to be saved
     * @return the saved topic
     */
    Long createTopic(Long supervisorId, TopicInputDto topic);

    /**
     * Modifies a topic
     *
     * @param id       of topic
     * @param topicDto topic to be modified
     * @return the modified topic
     */
    Long modifyTopic(Long id, TopicInputDto topicDto);

    /**
     * Archives the topic selected by the given id
     *
     * @param id
     */
    void archiveTopic(Long id);

    void addAttachment(Long topicId, AttachmentDto attachmentDto);

    void removeAttachment(Long topicId, Long attachmentId);

    void applyStudent(Long topicId, Long studentId);

    void removeApplication(Long topicId, Long studentId);

    Set<StudentDto> getAppliedStudents(Long topicId);

    Set<TopicDto> getTopicsAssignedToStudent(Long id);

    void acceptApplication(Long topicId, Long studentId);

    void setTopicDone(Long topicId);
}
