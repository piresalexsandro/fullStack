package br.com.alphapires.fullStack.services.validation;

import br.com.alphapires.fullStack.domain.Cliente;
import br.com.alphapires.fullStack.dto.ClienteDTO;
import br.com.alphapires.fullStack.repositories.ClienteRepository;
import br.com.alphapires.fullStack.resources.exception.FieldMessege;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;


public class ClienteUpdateValidator implements ConstraintValidator<ClienteUpdate, ClienteDTO> {

    @Autowired
    private ClienteRepository repository;

    @Autowired
    private HttpServletRequest httpServletRequest;


    @Override
    public void initialize(ClienteUpdate ann) {
    }

    @Override
    public boolean isValid(ClienteDTO objDto, ConstraintValidatorContext context) {

        Map<String, String> map = (Map<String, String>) httpServletRequest.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE         );

        Integer uriId = Integer.parseInt(map.get("id"));


        List<FieldMessege> fieldMessages = new ArrayList<>();

        Cliente aux =  repository.findByEmail(objDto.getEmail());

        if (Objects.nonNull(aux) && !aux.getId().equals(uriId)){
            fieldMessages.add(new FieldMessege("Email", "Email já existe"));
        }

        for (FieldMessege e : fieldMessages) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMessage())
                    .addPropertyNode(e.getFieldName()).addConstraintViolation();
        }

        return fieldMessages.isEmpty();
    }
}