package com.whyxs.common.bean.entity;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * <p>
 * 
 * </p>
 *
 * @author Bean
 * @since 2019-07-11
 */
public class PortalSite {

    private static final long serialVersionUID = 1L;

    /**
     * 站点名
     */
	private String siteName;
    /**
     * 站点图标
     */
	private String siteIcon;
    /**
     * 站点链接
     */
	private String siteLink;

	/**
	 * 排序
	 */
	private int siteOrder;


	public String getSiteName() {
		return siteName;
	}

	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}

	public String getSiteIcon() {
		return siteIcon;
	}

	public void setSiteIcon(String siteIcon) {
		this.siteIcon = siteIcon;
	}

	public String getSiteLink() {
		return siteLink;
	}

	public void setSiteLink(String siteLink) {
		this.siteLink = siteLink;
	}

	public int getSiteOrder() {
		return siteOrder;
	}

	public void setSiteOrder(int siteOrder) {
		this.siteOrder = siteOrder;
	}


	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}
