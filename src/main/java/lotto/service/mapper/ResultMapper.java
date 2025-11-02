package lotto.service.mapper;

import lotto.domain.Result;
import lotto.dto.ResultDto;

public class ResultMapper {
    public static ResultDto toDto(Result result) {
        return result.toDto();
    }
}
