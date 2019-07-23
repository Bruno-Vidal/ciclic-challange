package br.com.ciclic.cervejeira.resource.mapper;

import br.com.ciclic.cervejeira.business.model.EstiloCerveja;
import br.com.ciclic.cervejeira.resource.request.CervejaRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.NumberUtils;

@Component
public class CervejaMapper {

    public EstiloCerveja toService(CervejaRequest request) {
       return EstiloCerveja
                .builder()
                    .nome(request.getNome())
                    .temperaturaMaxima(request.getTemperaturaMaxima())
                    .temperaturaMinima(request.getTemperaturaMinima())
                .build();
    }

    public EstiloCerveja toService(CervejaRequest request, String cervejaId) {
        EstiloCerveja estiloCerveja = toService(request);
        estiloCerveja.setId(NumberUtils.parseNumber(cervejaId,Long.class));
        return estiloCerveja;
    }
}
