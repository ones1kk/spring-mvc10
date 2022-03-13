package hello.typeconverter.formatter;

import static org.assertj.core.api.Assertions.assertThat;

import hello.typeconverter.converter.IpPortToStringConverter;
import hello.typeconverter.converter.StringToIpPortConverter;
import hello.typeconverter.type.IpPort;
import org.junit.jupiter.api.Test;
import org.springframework.core.convert.support.DefaultConversionService;
import org.springframework.format.support.FormattingConversionService;

public class FormattingConversionServiceTest {

    @Test
    void formattingConversionService() {
        // FormattingConversionService ConversionService ???
        DefaultConversionService conversionService = new DefaultConversionService();
        FormattingConversionService formatter = new FormattingConversionService();

        // 컨버터 등록
        conversionService.addConverter(new StringToIpPortConverter());
        conversionService.addConverter(new IpPortToStringConverter());

        // 포멧터 등록
        formatter.addFormatter(new MyNumberFormatter());

        // 컨버터 사용
        IpPort ipPort = conversionService.convert("127.0.0.1:8080", IpPort.class);
        assertThat(ipPort).isEqualTo(new IpPort("127.0.0.1", 8080));

        // 포멧터 사용
        assertThat(formatter.convert(1000, String.class)).isEqualTo("1,000");
        assertThat(formatter.convert("1,000", Long.class)).isEqualTo(1000L);

    }

}
