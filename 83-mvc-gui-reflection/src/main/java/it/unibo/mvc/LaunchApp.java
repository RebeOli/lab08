package it.unibo.mvc;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import it.unibo.mvc.api.DrawNumberController;
import it.unibo.mvc.api.DrawNumberView;
import it.unibo.mvc.controller.DrawNumberControllerImpl;
import it.unibo.mvc.model.DrawNumberImpl;

/**
 * Application entry-point.
 */
public final class LaunchApp {
    private static final int LIMIT = 3;

    private LaunchApp() { }

    /**
     * Runs the application.
     *
     * @param args ignored
     * @throws ClassNotFoundException if the fetches class does not exist
     * @throws NoSuchMethodException if the 0-ary constructor do not exist
     * @throws InvocationTargetException if the constructor throws exceptions
     * @throws InstantiationException if the constructor throws exceptions
     * @throws IllegalAccessException in case of reflection issues
     * @throws IllegalArgumentException in case of reflection issues
     */
    public static void main(final String... args) {
        final var model = new DrawNumberImpl();
        final DrawNumberController app = new DrawNumberControllerImpl(model);
        /*
        app.addView(new DrawNumberSwingView());
        app.addView(new DrawNumberSwingView());
        app.addView(new DrawNumberStandardOutputView());
        */
        final Class<?> cl1;
        final Class<?> cl2;
        //Load class
        try {
            cl1 = Class.forName("it.unibo.mvc.view.DrawNumberSwingView");
            cl2 = Class.forName("it.unibo.mvc.view.DrawNumberStandardOutputView");
        } catch (final ClassNotFoundException e) {
            throw new IllegalStateException("Cannot load view classes", e);
        }
        final Constructor<?> cns1;
        final Constructor<?> cns2;
        try {
            cns1 = cl1.getConstructor();
            cns2 = cl2.getConstructor();
        } catch (final NoSuchMethodException e) {
            throw new IllegalStateException("Cannot find the constructor", e);
        }
        try {
            for (int i = 0; i < LIMIT; i++) {
                app.addView((DrawNumberView) cns1.newInstance());
                app.addView((DrawNumberView) cns2.newInstance());
            }
        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
            throw new IllegalStateException("Cannot instantiate views", e);
        }
    }
}
