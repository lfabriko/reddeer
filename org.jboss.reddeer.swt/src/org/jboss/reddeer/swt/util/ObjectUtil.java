package org.jboss.reddeer.swt.util;

import java.lang.reflect.Method;

import org.eclipse.swt.widgets.Widget;
import org.eclipse.swtbot.swt.finder.finders.UIThreadRunnable;
import org.eclipse.swtbot.swt.finder.results.Result;
import org.jboss.reddeer.swt.exception.SWTLayerException;

public class ObjectUtil {

	/**
	 * Invokes method
	 * @param object
	 * @param methodName
	 * @return
	 */
	public static Object invokeMethod(final Object object, String methodName) {
			
		final Method method;
		try {
			method = object.getClass().getMethod(methodName,
					new Class[0]);
		} catch (Exception e) {
			throw new SWTLayerException("Cannot get method : " + e.getMessage());
		}
		Widget widget = null;
		final Object result;
		if (object instanceof Widget) {
			widget = (Widget) object;
			result = UIThreadRunnable.syncExec(widget.getDisplay(),
					new Result<Object>() {
						public Object run() {
							try {
								return method.invoke(object, new Object[0]);
							} catch (Exception niceTry) {
							}
							return null;
						}
					});
		} else
			try {
				result = method.invoke(object, new Object[0]);
			} catch (Exception e) {
				throw new SWTLayerException("Cannot invoke method : " + e.getMessage());
			}

		return result;
	}

}
