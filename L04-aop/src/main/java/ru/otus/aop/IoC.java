package ru.otus.aop;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

class IoC {
    static Figure createFigure() {
        InvocationHandler handler = new FigureInvocationHandler(new Square());
        return (Figure) Proxy.newProxyInstance( IoC.class.getClassLoader(), new Class<?>[] {Figure.class}, handler);
    }

    static class FigureInvocationHandler implements InvocationHandler {
        private final Figure figure;

        FigureInvocationHandler(Figure figure) {
            this.figure = figure;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            Log logAnnotation = method.getAnnotation( Log.class );
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