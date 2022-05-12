package univ.tuit.backend.ext;

import univ.tuit.backend.utils.JsonUtil;

public interface JsonSerializable {
    default String toJson() {
        return JsonUtil.toJson(this);
    }

    default String toPrettyJson() {
        return JsonUtil.toPrettyJson(this);
    }
}
