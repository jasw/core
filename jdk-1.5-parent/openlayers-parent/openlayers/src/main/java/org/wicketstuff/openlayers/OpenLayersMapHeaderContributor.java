package org.wicketstuff.openlayers;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.WicketAjaxReference;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.markup.html.IHeaderResponse;
import org.apache.wicket.markup.html.WicketEventReference;
import org.apache.wicket.request.resource.JavaScriptResourceReference;
import org.apache.wicket.request.resource.ResourceReference;

public class OpenLayersMapHeaderContributor extends Behavior {
	private static final long serialVersionUID = 1L;

	// We have some custom JavaScript.
	private static final ResourceReference WICKET_OMAP_JS = new JavaScriptResourceReference(
			OpenLayersMap.class, "wicket-openlayersmap.js");

	private final boolean developmentMode;

	private final String openLayersVersion;

	private static String getURL(boolean developmentMode, String version) {
		String prefix = "http://dev.openlayers.org/releases/OpenLayers-"
				+ version;

		if (developmentMode) {
			return prefix + "/lib/OpenLayers.js";
		} else {
			// production mode
			return prefix + "/OpenLayers.js";
		}

	}

	/**
	 * 
	 * @param developmentMode
	 * @param openLayersVersion
	 *            the version of openlayers to use: like 2.9.1 or 2.8. Comes
	 *            from the list available here:
	 *            http://dev.openlayers.org/releases
	 */
	public OpenLayersMapHeaderContributor(final boolean developmentMode,
			final String openLayersVersion) {
		super();
		this.developmentMode = developmentMode;
		this.openLayersVersion = openLayersVersion;

	}

	@Override
	public void renderHead(Component c, IHeaderResponse response) {

		String url = getURL(developmentMode, openLayersVersion);

		response.renderJavaScriptReference(url);
		response.renderJavaScriptReference(WicketEventReference.INSTANCE);
		response.renderJavaScriptReference(WicketAjaxReference.INSTANCE);
		response.renderJavaScriptReference(WICKET_OMAP_JS);

	}

}
