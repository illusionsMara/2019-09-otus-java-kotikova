package ru.otus.aop;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class IoC {
    private static Map<Method, Annotation> map = new HashMap();

    static Figure createFigure() {
        Class clazz = Figure.class;
        Method[] methods = clazz.getMethods();
        for(int i = 0; i < methods.length; i++) {
            map.put( methods[i], methods[i].getAnnotation(Log.class));
        }
        InvocationHandler handler = new FigureInvocationHandler(new Square());
        return (Figure)Proxy.newProxyInstance(IoC.class.getClassLoader(), new Class<?>[] {clazz}, handler);
    }

    static class FigureInvocationHandler implements InvocationHandler {
        private final Figure figure;

        FigureInvocationHandler(Figure figure) {
            this.figure = figure;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            Log logAnnotation = (Log)map.get(method);
            if(logAnnotation != null) {
                StringBuilder log = new StringBuilder("start method: " + method.getName());
                LoggedTarget[] loggedTargets = logAnnotation.loggedTarget();
                for( LoggedTarget target: loggedTargets ) {
                    if( target == LoggedTarget.PARAMS ) {
                        log.append("; params: " + Arrays.toString(args));
                    }
                    if( target == LoggedTarget.RETURN ) {
                        log.append( "; return type: " + method.getReturnType());
                    }
                }
                System.out.println(log);
            }
            return method.invoke(figure, args);
        }
    }
}
