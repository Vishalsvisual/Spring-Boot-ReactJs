package emp.management.utils;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MapperUtil {

    @Autowired
    private ModelMapper mapper;

    public <S, T> List<T> mapList(List<S> source, Class<T> targetClass) {
        return source
                .stream()
                .map(element -> mapper.map(element, targetClass))
                .toList();
    }

    public <S, T> T map(S source, Class<T> targetClass) {
        return mapper.map(source, targetClass);
    }
}
