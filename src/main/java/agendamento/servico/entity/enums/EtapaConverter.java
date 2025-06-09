package agendamento.servico.entity.enums;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class EtapaConverter implements AttributeConverter<Etapa, String> {

    @Override
    public String convertToDatabaseColumn(Etapa attribute) {
        return attribute != null ? attribute.getValor() : null;
    }

    @Override
    public Etapa convertToEntityAttribute(String dbData) {
        return dbData != null ? Etapa.valueOf(dbData) : null;
    }
}
