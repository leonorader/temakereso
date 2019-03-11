package temakereso.service.implementation;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import temakereso.entity.Role;
import temakereso.entity.Supervisor;
import temakereso.helper.SupervisorDto;
import temakereso.repository.SupervisorRepository;
import temakereso.service.RoleService;
import temakereso.service.SupervisorService;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SupervisorServiceImplementation implements SupervisorService {

    private final SupervisorRepository supervisorRepository;

    private final PasswordEncoder passwordEncoder;

    private final ModelMapper modelMapper;

    private final RoleService roleService;

    @Override
    public List<SupervisorDto> getAll() {
        return supervisorRepository.findAll()
                .stream()
                .map(supervisor -> modelMapper.map(supervisor, SupervisorDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public SupervisorDto createSupervisor(Supervisor supervisor) {
        Role supervisorRole = roleService.findByName("SUPERVISOR");
        supervisor.getAccount().setRoles(Arrays.asList(supervisorRole != null ? supervisorRole : new Role("SUPERVISOR")));
        supervisor.getAccount().setPassword(passwordEncoder.encode(supervisor.getAccount().getPassword()));
        return modelMapper.map(supervisorRepository.save(supervisor), SupervisorDto.class);
    }

    @Override
    public SupervisorDto modifySupervisor(Supervisor supervisor) {
        return modelMapper.map(supervisorRepository.save(supervisor), SupervisorDto.class);
    }

    @Override
    public SupervisorDto getOneById(Long id) {
        return modelMapper.map(supervisorRepository.findOne(id), SupervisorDto.class);
    }

    @Override
    public List<SupervisorDto> getUnconfirmed() {
        return supervisorRepository.findByConfirmedFalse()
                .stream()
                .map(supervisor -> modelMapper.map(supervisor, SupervisorDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public void confirm(Long id) {
        Supervisor supervisor = supervisorRepository.findOne(id);
        supervisor.setConfirmed(Boolean.TRUE);
        supervisorRepository.save(supervisor);
    }

    // DO NOT FORGET csak belső használatra, mapper nélkül!!
    @Override
    public Supervisor findOneById(Long supervisorId) {
        return supervisorRepository.findOne(supervisorId);
    }

    @Override
    public SupervisorDto findByAccountId(Long accountId) {
        Supervisor supervisor = supervisorRepository.findByAccountId(accountId);
        return supervisor != null ? modelMapper.map(supervisor, SupervisorDto.class) : null;
    }

}