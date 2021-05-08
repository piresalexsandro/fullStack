package br.com.alphapires.fullStack.services.validation;

import br.com.alphapires.fullStack.domain.enums.TipoCliente;
import br.com.alphapires.fullStack.dto.ClienteNewDTO;
import br.com.alphapires.fullStack.resources.exception.FieldMessege;
import br.com.alphapires.fullStack.services.validation.utils.BR;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;
import java.util.ArrayList;
import java.util.Objects;


public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, ClienteNewDTO> {

    @Override
    public void initialize(ClienteInsert ann) {
    }

    @Override
    public boolean isValid(ClienteNewDTO objDto, ConstraintValidatorContext context) {
        List<FieldMessege> fieldMessages = new ArrayList<>();

        if (Objects.nonNull(objDto.getCpfOuCnpj())){
            if (objDto.getTipo().equals(TipoCliente.PESSOAFISICA.getCodigo())
                && !BR.isValidCpf(objDto.getCpfOuCnpj())){
                fieldMessages.add(new FieldMessege("CpfOuCnpj", "CPF Invalido"));
            }
            if (objDto.getTipo().equals(TipoCliente.PESSOAJURIDICA.getCodigo())
                    && !BR.isValidCnpj(objDto.getCpfOuCnpj())){
                fieldMessages.add(new FieldMessege("CpfOuCnpj", "CNPJ Invalido"));
            }
        }

        for (FieldMessege e : fieldMessages) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMessage())
                    .addPropertyNode(e.getFieldName()).addConstraintViolation();
        }
        return fieldMessages.isEmpty();
    }
}