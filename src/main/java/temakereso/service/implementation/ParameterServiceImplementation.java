package temakereso.service.implementation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import temakereso.entity.Parameter;
import temakereso.helper.Constants;
import temakereso.repository.ParameterRepository;
import temakereso.service.FileService;
import temakereso.service.ParameterService;

import java.io.IOException;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ParameterServiceImplementation implements ParameterService {

    private final ParameterRepository parameterRepository;

    private final FileService fileService;

    @Override
    public List<Parameter> getAll() {
        return parameterRepository.findAll();
    }

    @Override
    public Parameter findByIdentifier(String identifier) {
        if (!parameterRepository.existsByIdentifier(identifier)) {
            log.error("No parameter exists with identier: {}", identifier);
            throw new IllegalArgumentException(Constants.PARAMETER_NOT_EXISTS);
        }
        return parameterRepository.findByIdentifier(identifier);
    }

    @Override
    public Long getBscTopicFormId() {
        return Long.parseLong(findByIdentifier("BSC_TOPIC_FORM").getValue());
    }

    @Override
    public Long getMscTopicFormId() {
        return Long.parseLong(findByIdentifier("MSC_TOPIC_FORM").getValue());
    }

    @Override
    public Long getBscConsultationFormId() {
        return Long.parseLong(findByIdentifier("BSC_CONSULTATION_FORM").getValue());
    }

    @Override
    public Long getMscConsultationFormId() {
        return Long.parseLong(findByIdentifier("MSC_CONSULTATION_FORM").getValue());
    }

    @Override
    public Integer getArchiveTimeout() {
        return Integer.parseInt(findByIdentifier("ARCHIVE_TIMEOUT_IN_MONTHS").getValue());
    }

    @Override
    public void modifyForm(String identifier, MultipartFile file) throws IOException {
        Long id = fileService.saveFile(file);
        Parameter parameter = parameterRepository.findByIdentifier(identifier);
        parameter.setValue(id.toString());
        parameterRepository.save(parameter);
    }

}
