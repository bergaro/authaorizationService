package ru.netology.authService.conigurer;

import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.validation.DataBinder;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import ru.netology.authService.exception.InvalidCredentials;
import ru.netology.authService.repository.User;

import javax.validation.Valid;

@Component
public class UserHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {

    private static final int ERROR_POSITION = 1;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(UserResolver.class);
    }

    @Override
    public Object resolveArgument( MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        String userName = webRequest.getParameter("user");
        String userPassword = webRequest.getParameter("password");
        String binderError;
        WebDataBinder binder;
        User user = new User().setName(userName).setPassword(userPassword);
        if(parameter.hasParameterAnnotation(Valid.class)) {
            binder = binderFactory.createBinder(webRequest, user, DataBinder.DEFAULT_OBJECT_NAME);
            binder.validate();
            binderError = binder.getBindingResult().toString();
            if(findBinderError(binderError)) {
                String messageError = "Incorrect field length!";
                throw new InvalidCredentials(messageError);
            }
        }
        return user;
    }
    /**
     * Проверяет счётчик ошибок в сообщении org.springframework.validation.BeanPropertyBindingResult:
     * Если счётчик ошибок = 0 errors, возвращает - false (ошибок не обнаружено)
     * Если счётчик ошибок >= 1 errors, возвращает - true (обнаружены ошибки)
     * @param binderErrorMsg binder.getBindingResult().toString()
     * @return Результат наличия ошибок по результату работы метода validate()
     */
    private boolean findBinderError(String binderErrorMsg) {
        String[] binderStringComponents = binderErrorMsg.split(" ");
        return Integer.parseInt(binderStringComponents[ERROR_POSITION]) != 0;
    }
}
